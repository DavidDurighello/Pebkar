package com.app.pebkar.Tools;

/**
 * Created by Lyyn on 21-11-15.
 */

import java.text.Normalizer;

/**
 * Classe abstraite permettant d'effectuer diverses opérations sur les strings
 * Exemple d'utilisation :
 *     String test = StrTools.doRegexAccent('e');
 *     System.out.println(test);
 *     --> "eéèêë"
 */
public abstract class StrTools {
    /**
     * Temp - Ajoute les variations d'accentuations
     * @throws Exception
     */
    public static String doRegexAccent(char c) {
        String result;

        switch(c) {
            case 'a':
                result = "aáàäâã";
                break;
            case 'e':
                result = "eéèêë";
                break;
            case 'i':
                result = "iíìïî";
                break;
            case 'o':
                result = "oôöóòõ";
                break;
            case 'u':
                result = "uúùûü";
                break;
            case 'y':
                result = "yýÿ";
                break;
            default:
                result = ((Character) c).toString();
        }

        return result;
    }

    /**
     * Fonction qui retire toute forme d'accentuation d'un string
     * Basé sur : http://stackoverflow.com/questions/3322152/is-there-a-way-to-get-rid-of-accents-and-convert-a-whole-string-to-regular-lette
     * @param str le string à modifier
     * @return le string sans accent
     */
    public static String removeAccent(String str) {
        str = Normalizer.normalize(str.substring(0), Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }
}
