/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package czak.manager;

/**
 *
 * @author Paulina
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        emf = Persistence.createEntityManagerFactory("CzakHiberPU");
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
