import os
import sqlite3
from flask import Flask, request, session, g, url_for, abort, make_response, current_app, jsonify
from datetime import datetime, timedelta
from functools import update_wrapper


class Publicacion(object):                                  # TODO: Limitar setters

    def __init__(self):
        self._id = None
        self._title = None
        self._content = None
        self._fecha = None

    def loadfromdb(self, idd):
        db = get_db()
        cur = db.execute('SELECT title, content, fecha FROM PUBLICACIONES WHERE id == ?', (idd,))
        entries = cur.fetchall()
        entry = entries[0]
        self.setid(idd)
        self.settitle(entry['title'])
        self.setcontent(entry['content'])
        self.setfecha(entry['fecha'])

    def loadfromjson(self, json):
        pub = json['publicacion']
        self.settitle(pub.get('title', self.gettitle()))
        self.setcontent(pub.get('content', self.getcontent()))
        self.setfecha(datetime.now().strftime("%Y-%m-%d %H:%M:%S"))

        if not self.gettitle() or not self.getcontent():
            raise ValueError

    def getjson(self):

        return jsonify(
            publicacion=dict(
                id=self.getid(),
                title=self.gettitle(),
                content=self.getcontent(),
                fecha=self.getfecha()
            )
        )

    def saveintodb(self):
        db = get_db()

        cur = db.execute(
            'INSERT INTO PUBLICACIONES (title, content, fecha) VALUES (?,?,?)',
            (self.gettitle(), self.getcontent(), self.getfecha())
        )

        self.setid(cur.lastrowid)
        db.commit()

    def deletefromdb(self):
        db = get_db()
        db.execute('DELETE FROM PUBLICACIONES WHERE id == ?', (self.getid(),))
        db.commit()

    def updatedb(self):
        db = get_db()

        db.execute(
            'UPDATE PUBLICACIONES SET title = ?, content = ?, fecha = ? WHERE id == ?',
            (self.gettitle(), self.getcontent(), self.getfecha(), self.getid())
        )

        db.commit()

    def settitle(self, title):
        self._title = title

    def setcontent(self, content):
        self._content = content

    def setfecha(self, fecha):
        self._fecha = fecha

    def setid(self, idd):
        self._id = idd

    def gettitle(self):
        return self._title

    def getcontent(self):
        return self._content

    def getfecha(self):
        return self._fecha

    def getid(self):
        return public_id(self._id)


class User(object):

    def __init__(self):
        self._user = None
        self._nombre = None
        self._email = None
        self._pass = None

    def loaduserfromdb(self, email):
        db = get_db()
        cur = db.execute('SELECT pass FROM USUARIOS WHERE email == ?', (email,))
        entries = cur.fetchall()
        entry = entries[0]
        self.setpass(entry['pass'])
        self.setemail(entry['user'])

    def loaduserfromjson(self, json):
        if 'registro' in json:
            us = json['registro']
            self.setuser(us['acc'])
            self.setpass(us['pwd'])
            self.setnombre(us['nombre'])
            self.setemail(us['email'])
        elif 'usuario' in json:
            us = json['usuario']
            self.setemail(us['acc'])            # Esto habria que cambiar
            self.setpass(us['pwd'])
        else:
            raise TypeError

    def registerindb(self):
        db = get_db()
        db.execute('INSERT INTO USUARIOS (user, nombre, email, pass) VALUES (?,?,?,?)',
                   (self.getuser(), self.getnombre(), self.getemail(), self.getpass()))
        db.commit()

    def matchdb(self):
        db = get_db()
        cur = db.execute('SELECT pass FROM USUARIOS WHERE email == ?', (self.getemail(),))
        entry = cur.fetchall()[0]

        return entry['pass'] == self.getpass()

    def setpass(self, pwd):
        self._pass = pwd

    def setuser(self, user):
        self._user = user

    def setnombre(self, nombre):
        self._nombre = nombre

    def setemail(self, email):
        self._email = email

    def getnombre(self):
        return self._nombre

    def getuser(self):
        return self._user

    def getemail(self):
        return self._email

    def getpass(self):
        return self._pass


app = Flask(__name__)
app.config.from_object(__name__)

app.config.update(dict(
    DATABASE='CONFIGURE DATABASE HERE',
    SECRET_KEY='(\xcc\xa3+\xf6-\xa2\xe9\x98\tkd\n\xad\xa0\n9\xf0\x8bv\xefs\xd9\x04',
))
app.config.from_envvar('FCEFYNAPP_SETTINGS', silent=True)


def connect_db():
    """Conexion con la db"""

    rv = sqlite3.connect(app.config['DATABASE'])
    rv.row_factory = sqlite3.Row
    return rv


def init_db():
    db = get_db()
    with app.open_resource('schema.sql', mode='r') as f:
        db.cursor().executescript(f.read())
    db.commit()


@app.cli.command('initdb')
def initdb_command():
    init_db()
    print('DB inicializada')


def get_db():

    if not hasattr(g, 'sqlite_db'):
        g.sqlite_db = connect_db()
    return g.sqlite_db


@app.teardown_appcontext
def close_db(error):
    if hasattr(g, 'sqlite_db'):
        g.sqlite_db.close()


"""
                CODIGO PARA CROSSDOMAIN
                
            http://flask.pocoo.org/snippets/56/
"""


def crossdomain(origin=None, methods=None, headers=None,
                max_age=21600, attach_to_all=True,
                automatic_options=True):
    if methods is not None:
        methods = ', '.join(sorted(x.upper() for x in methods))
    if headers is not None and not isinstance(headers, str):
        headers = ', '.join(x.upper() for x in headers)
    if not isinstance(origin, str):
        origin = ', '.join(origin)
    if isinstance(max_age, timedelta):
        max_age = max_age.total_seconds()

    def get_methods():
        if methods is not None:
            return methods

        options_resp = current_app.make_default_options_response()
        return options_resp.headers['allow']

    def decorator(f):
        def wrapped_function(*args, **kwargs):
            if automatic_options and request.method == 'OPTIONS':
                resp = current_app.make_default_options_response()
            else:
                resp = make_response(f(*args, **kwargs))
            if not attach_to_all and request.method != 'OPTIONS':
                return resp

            h = resp.headers

            h['Access-Control-Allow-Origin'] = origin
            h['Access-Control-Allow-Methods'] = get_methods()
            h['Access-Control-Max-Age'] = str(max_age)
            if headers is not None:
                h['Access-Control-Allow-Headers'] = headers
            return resp

        f.provide_automatic_options = False
        return update_wrapper(wrapped_function, f)
    return decorator


def public_id(idd):
    return url_for('get_publicacion', publicacion_id=idd, _external=True)


@app.route('/publicaciones/', methods=['GET'])
@crossdomain(origin='*')
def get_allpub():
    db = get_db()
    cur = db.execute('SELECT id, title FROM PUBLICACIONES ORDER BY ID DESC')
    entries = cur.fetchall()
    return jsonify(
        publicaciones=dict(publicacion=[dict(id=public_id(entry['id']), title=entry['title']) for entry in entries])
    )


@app.route('/publicaciones/<int:publicacion_id>/', methods=['GET'])
@crossdomain(origin='*')
def get_publicacion(publicacion_id):
    pub = Publicacion()
    try:
        pub.loadfromdb(publicacion_id)
    except IndexError:
        abort(404)

    return pub.getjson()


@app.route('/publicaciones/', methods=['POST'])
def crear_publicacion():

    if not session.get('logged_in'):
        abort(401)

    if not request.is_json:
        abort(400)

    pub = Publicacion()
    try:
        pub.loadfromjson(request.get_json())
    except (ValueError, TypeError):
        abort(400)
    pub.saveintodb()

    return pub.getjson(), 201


@app.route('/registro/', methods=['POST'])
def registrar():
    if not request.is_json:
        abort(400)

    user = User()

    try:
        user.loaduserfromjson(request.get_json())
    except (TypeError, KeyError):
        abort(400)

    user.registerindb()

    return jsonify(registrado=True)


@app.route('/publicaciones/<int:publicacion_id>/', methods=['DELETE'])
def delete_publicacion(publicacion_id):

    if not session.get('logged_in'):
        abort(401)

    pub = Publicacion()
    pub.loadfromdb(publicacion_id)
    pub.deletefromdb()

    return pub.getjson()


@app.route('/publicaciones/<int:publicacion_id>/', methods=['PUT'])
def modify_publicacion(publicacion_id):

    if not session.get('logged_in'):
        abort(401)

    if not request.is_json:
        abort(400)

    pub = Publicacion()
    pub.loadfromdb(publicacion_id)
    try:
        pub.loadfromjson(request.get_json())
    except TypeError:
        abort(400)

    pub.updatedb()

    return pub.getjson()


@app.route('/login/', methods=['POST'])
def login():
    if not request.is_json:
        abort(400)
    user = User()
    user.loaduserfromjson(request.get_json())
    if user.matchdb():
        session['logged_in'] = True
        return jsonify(logged=True)
    else:
        return jsonify(logged=False)


@app.route('/logout/')
def logout():
    session.pop('logged_in', None)

    return jsonify(logged_out=True)

# TODO: LIMITAR SETTERS SEGUN REQUERIMIENTOS, IMPLEMENTAR OBSERVER, IMPLEMENTAR PERMISO DE ADMIN, LOGS
