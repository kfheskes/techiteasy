package nl.novi.techiteasy.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


public class ControllerHelper {

    // Declareert een publieke statische methode genaamd 'checkForBindingResult'.
    // De methode neemt een BindingResult-object als parameter.
    public static String checkForBindingResult (BindingResult br) {
        // Maakt een nieuw StringBuilder-object aan. StringBuilder wordt gebruikt voor efficiënte string-samenvoeging.
        StringBuilder sb = new StringBuilder();
        // Begint een for-each lus die over elk FieldError in de veldfouten van de BindingResult itereert.
        for (FieldError fe : br.getFieldErrors()) {
            // Voegt de naam van het veld dat een fout heeft toe aan de StringBuilder.
            sb.append(fe.getField());
            // Voegt een scheidingsteken (dubbele punt en spatie) toe aan de StringBuilder na de veldnaam.
            sb.append(" : ");
            // Voegt de standaardfoutmelding geassocieerd met de veldfout toe aan de StringBuilder.
            sb.append(fe.getDefaultMessage());
            // Voegt een nieuwe regel karakter toe aan de StringBuilder, om deze fout van de volgende te scheiden.
            sb.append("\n");
        }
        // Zet de StringBuilder om in een String en retourneert deze.
        // Deze string bevat een samengevoegde lijst van alle veldfouten en hun berichten.
        return sb.toString();
    }
}
