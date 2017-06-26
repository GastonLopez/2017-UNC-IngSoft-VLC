package fcefyn.fcefynapp.ValidacionYEncriptado;

import org.junit.Test;

import static org.junit.Assert.*;



public class ValidatorUtilTest  {

    @Test
    public void EmailValido() {
        assertTrue(ValidatorUtil.validateEmail("maria@gmail.com"));
    }

    @Test
    public void EmailInvalido(){
        assertFalse(ValidatorUtil.validateEmail("mariagmail.com"));
        assertFalse(ValidatorUtil.validateEmail("maria@gmailcom"));
        assertFalse(ValidatorUtil.validateEmail("mariagmailcom"));
    }

}