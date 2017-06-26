package fcefyn.fcefynapp.ValidacionYEncriptado;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gastón on 25/6/2017.
 */
public class HashTest {

    @Test
    public void testMd5() throws Exception {
        String contraseña1="xxxxx";
        String contraseña2="yyyyy";
        assertNotEquals(Hash.md5(contraseña1),contraseña1);
        assertNotEquals(Hash.md5(contraseña2),contraseña2);
        assertNotEquals(Hash.md5(contraseña2),Hash.md5(contraseña1));
        assertEquals(Hash.md5(contraseña2),Hash.md5(contraseña2));
    }
}