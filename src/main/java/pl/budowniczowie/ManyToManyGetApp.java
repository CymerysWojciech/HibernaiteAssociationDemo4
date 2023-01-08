package pl.budowniczowie;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.budowniczowie.entity.*;

import java.util.List;

public class ManyToManyGetApp {
    public static void main(String[] args){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Employee.class);
        conf.addAnnotatedClass(Training.class);
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);
        conf.addAnnotatedClass(Department.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        String getTraining = "from Training";

        Query query = session.createQuery(getTraining);
        List<Training>trainings = query.getResultList();

        for (Training training: trainings){
            System.out.println(training);
            for (Employee employee:training.getEmployees()){
                System.out.println(employee);
            }

        }


        session.getTransaction().commit();


        factory.close();
    }
}
