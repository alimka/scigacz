/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Managery;

import ScigaczDB.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author pedros
 * W tej oto klasie znajduje się manager przechowujący entity managera dla pozostałych managerów.
 */
public class ManagerNadManagery {

    EntityManagerFactory emf;
    EntityManager otoon;

    /**
     * Tworzy EntityManagerFactory
     */
    public ManagerNadManagery() {
        emf = Persistence.createEntityManagerFactory("ScigaczPU");
    }

    /**
     * Zwraca aktualnie utworzony EntityManager
     * @return  EntityManager dla jednostki ScigaczPU.
     */
    public EntityManager DajEM() {
        if (otoon == null) {
            otoon = emf.createEntityManager();
        }
        if (!otoon.isOpen()) {
            otoon = emf.createEntityManager();
        }

        return otoon;
    }
}
