package cu.edu.cujae.structdb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class Validator {
    /**
     * Valida una cadena de caracteres para el formato de las placas de carro que se usa específicamente en Cuba.
     * @param numberPlate Recibe como parámetro un String que debe ser el número de placa de un carro.
     * @return Retorna un booleano con valor verdadero si el String fue correctamente validado, y falso en caso contrario.
     */
    boolean ValidatePlate (String numberPlate){
        boolean Validated = false;
       String PlateFormat = "^[T]{1}[0-9]{6}$";
    /* Métodos usados en validaciones de este tipo de campos */
        Pattern pattern = Pattern.compile(PlateFormat);
        Matcher matcher = pattern.matcher(numberPlate);

        if(matcher.matches()){
        Validated = true;
        }
        return Validated;
    }

    /**
     * Valida una cadena de texto para que no contenga espacios en blanco al principio y al final, y solo caracteres alfabéticos.
     * @param chain Recibe como parámetro una cadena de texto de hasta 50 caracteres.
     * @return Retorna true o false según resulte la validación.
     */
    public boolean validateName(String chain) {
        boolean validated = false;
        String trimmedBrand = chain.trim();
        if (trimmedBrand.length() > 0 && trimmedBrand.length() <= 50 && trimmedBrand.matches("^[a-zA-Z]*$")) {
            validated = true;
        }
        return validated;
    }

    /**
     * Valida una cadena de caracteres con el formato del identificador de un pasaporte.
     * @param Passport Recibe como parámetro un String que debe ser el identificador del pasaporte.
     * @return Retorna un booleano con valor verdadero si el String fue correctamente validado, y falso en caso contrario.
     */
    boolean ValidatePassport (String Passport){
        boolean Validated = false;
        String PassportFormat = "^[A-Z]{3}[0-9]{6}$";

        Pattern pattern = Pattern.compile(PassportFormat);
        Matcher matcher = pattern.matcher(Passport);

        if(matcher.matches()){
            Validated = true;
        }
        return Validated;
}
    /**
     * Valida una cadena de texto (número telefónico) que recibe como parámetro. Verificando que cumpla con que su longitud sea de entre 6 y 9 caracteres,
     * además que todos ellos sean numéricos.
     * @param phoneNumber Recibe como parámetro una cadena de texto.
     * @return Retorna true o false según resulte la validación.
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() >= 6 && phoneNumber.length() <= 9) {
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valida una cadena de caracteres para que cumpla con el formato de un número de DNI.
     * Verificando que cumpla con que su longitud sea de 11 caracteres, además que todos ellos sean numéricos.
     * @param dniNumber Recibe como parámetro una cadena de texto.
     * @return Retorna true o false según resulte la validación.
     */
    public boolean validatePhoneNumberDNInumber(String dniNumber) {
        if (dniNumber.length() == 11) {
            return false;
        }
        for (int i = 0; i < dniNumber.length(); i++) {
            if (!Character.isDigit(dniNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}



