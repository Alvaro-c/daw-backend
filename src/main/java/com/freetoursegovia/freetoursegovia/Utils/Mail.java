package com.freetoursegovia.freetoursegovia.Utils;

import com.freetoursegovia.freetoursegovia.model.Booking;
import com.freetoursegovia.freetoursegovia.model.Client;
import com.freetoursegovia.freetoursegovia.model.Form;
import com.freetoursegovia.freetoursegovia.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Class to send confirmation email
 */
public class Mail {

    public boolean sendEmail(Client client) {

        final String username = "contacto@freetoursegovia.org";
        final String password = "Andifyougo(12)";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.hostinger.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("contacto@freetoursegovia.org"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(client.getEmail())
            );
            int total = client.getAdults() + client.getChildren();
            message.setSubject("Confirmación de Reserva - FreeTourSegovia.org");
            message.setText(

                    "Estimado " + client.getName() + ", \n" +
                            "Confirmamos su reserve de " + total + " plazas para el día " + Utils.SQLtoString(client.getVisitDate()) + " en nuestro free tour.\n" +
                            "\nEl punto de encuentro es a los pies de Acueducto, en Plaza del Azoguejo N1. \n" +
                            "\nEl guía llevará una camiseta naranja y un paraguas como el del logo de la empresa. \n" +
                            "\nPuede leer más acerca de la visita que ofrecemos en esta dirección: https://freetoursegovia.org/la_visita\n" +
                            "\nSi necesita ponerse en contacto con nosotros para resolver alguna duda, o en caso de que necesite cancelar la reserva, puede hacerlo escribiendo a esta dirección contacto@freetoursegovia.org o respondiendo a este mismo correo.\n" +
                            "\nMuchas gracias por reservar con nosotros y esperamos verle pronto !\n" +
                            "\n" +
                            "\nAtentamente, \n" +
                            "FreeTourSegovia.org\n");

            Transport.send(message);

            System.out.println("Done");

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean sendBookingToMe(Client client) {

        final String username = "contacto@freetoursegovia.org";
        final String password = "Andifyougo(12)";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.hostinger.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("contacto@freetoursegovia.org"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("contacto@freetoursegovia.org")
            );
            int total = client.getAdults() + client.getChildren();
            message.setSubject("Nueva reserva, Fecha: " + client.getVisitDate() + "; Plazas: " + total);
            message.setText(

                    "Reserva:" + "\n" +
                            "Fecha: " + client.getVisitDate() + "\n" +
                            "Nombre: " + client.getName() + "\n" +
                            "Apellido: " + client.getSurname() + "\n" +
                            "Personas: " + total + "\n" +
                            "Adultos: " + client.getAdults() + "\n" +
                            "Niños: " + client.getChildren() + "\n" +
                            "email: " + client.getEmail() + "\n" +

                            "teléfono: " + client.getPhone() + "\n" +
                            "Comentario: " + client.getComment() + "\n");

            Transport.send(message);

            System.out.println("Done");

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();

            return false;
        }
    }


    public boolean sendEmailToUser(Booking booking) {

        final String username = "contacto@freetoursegovia.org";
        final String password = "Andifyougo(12)";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.hostinger.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("contacto@freetoursegovia.org"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(booking.getUser().getEmail())
            );
            int total = booking.getAdults() + booking.getChildren();
            message.setSubject("Confirmación de Reserva - FreeTourSegovia.org");
            message.setText(

                    "Estimado " + booking.getUser().getName() + ", \n" +
                            "Confirmamos su reserve de " + total + " plazas para el día " + Utils.SQLtoString(booking.getDate()) + " en nuestro free tour.\n" +
                            "\nEl punto de encuentro es a los pies de Acueducto, en Plaza del Azoguejo N1. \n" +
                            "\nEl guía llevará una camiseta naranja y un paraguas como el del logo de la empresa. \n" +
                            "\nPuede leer más acerca de la visita que ofrecemos en esta dirección: https://freetoursegovia.org/la_visita\n" +
                            "\nSi necesita ponerse en contacto con nosotros para resolver alguna duda, o en caso de que necesite cancelar la reserva, puede hacerlo escribiendo a esta dirección contacto@freetoursegovia.org o respondiendo a este mismo correo.\n" +
                            "\nMuchas gracias por reservar con nosotros y esperamos verle pronto !\n" +
                            "\n" +
                            "\nAtentamente, \n" +
                            "FreeTourSegovia.org\n");

            Transport.send(message);

            System.out.println("Done");

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean sendBookingToMe(Form form) {

        final String username = "contacto@freetoursegovia.org";
        final String password = "Andifyougo(12)";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.hostinger.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("contacto@freetoursegovia.org"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("segoviafreetour@gmail.com")
            );

            message.setSubject("Nueva formulario de contacto");
            message.setText(

                    "Formulario:" + "\n" +

                            "Nombre: " + form.getName() + "\n" +
                            "Apellido: " + form.getSurname() + "\n" +
                            "Email: " + form.getEmail() + "\n" +
                            "Teléfono: " + form.getPhone() + "\n" +
                            "Comentario: " + form.getMessage() + "\n");

            Transport.send(message);

            System.out.println("Done");

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();

            return false;
        }
    }

}