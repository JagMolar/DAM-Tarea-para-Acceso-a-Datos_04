/*
 * TAREA AD04.
 * Una empresa dispone de una base de datos contiene las tablas con 
 * la información necesaria para su gestión. Las tablas son las siguientes:
 *  Tabla DEPT que contiene información de los diferentes departamentos 
 *   que tiene la empresa. La clave principal DEPTNO.
 *  Tabla EMP que contiene la información de los diferentes empleados 
 *   que tiene la empresa. Tiene como clave principal EMPNO y ajenas
 *   DEPTNO que relaciona con la tabla departamentos y MGR que establece 
 *   la relación con la misma tabla mostrando ser jefe de.
 *
 * La base de datos que se utilizará será Oracle.
 *
 * Mapea las tablas utilizando Hibernate con NetBeans y realiza un proyecto 
 * Java llamado HibernateOracle que obtenga lo siguiente:
 * 1. Crea la base de datos.
 * 2. Configura y crea la ORM Hibernate.
 * 3. Realiza una inserción y un borrado sobre la tabla EMP.
 * 4. Obtener un listado sobre las tablas EMP y DEPT que visualice empno, 
 *    ename, sal, dname y loc.
 *
 */
package hibernateoracle;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import recursos.NewHibernateUtil;

/**
 *
 * @author Juan A. García Muelas <juangmuelas@gmail.com>
 * @version 1
 * @since 12/01/22
 */
public class HibernateOracle {
    
    /**
     * Constructor vacío
     */

    public HibernateOracle() {
    }
    
    
    /**
     * Antes de crear el main, implementamos la instancia para
     * trabajar con la BD, mediante el patón Singleton
     */
    private Session sesion;
    SessionFactory sesionFact;
    
    //Obtenemos la sesión asegurando iniciarla si es null
    public Session getSession() {
        if (sesion == null) {
            SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
            sesion = sessionFactory.openSession();
        }
        return sesion;
    }
    
    /**
     * Creamos un método genérico que nos guarda en una lista los 
     * objetos query devueltos y así hacer más legible la
     * creción y lectura de código en cada petición.
     * @param ConsultaSQL String que recoge la query.
     */

    public <T> List<T> consulta(String ConsultaSQL) {
        Query query = getSession().createQuery(ConsultaSQL);
        return query.list();
    }
       
    public void insertarEmpl(Emp empleado) {
        /**
         * PASOS:
         * Para utilizar la persistencia en Hibernate, el SessionFactory
         * Comenzamos luego la transacción
         * save para introducir el objeto
         * commit para realizar la transacción y sincronizar con la BD
         * Cierra sesión
         */
        
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction transaccion = session.beginTransaction();
        session.save(empleado);
        transaccion.commit();
        session.close();
    }

    public void borrarEmpl(Emp empleado) {
        /**
         * PASOS:
         * Para utilizar la persistencia en Hibernate, el SessionFactory
         * Comenzamos luego la transacción
         * delete para borrar objetos persistentes
         * commit para realizar la transacción y sincronizar con la BD
         * Cierra sesión
         */
     
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction transaccion = session.beginTransaction();
        session.delete(empleado);
        transaccion.commit();
        session.close();
    }
    
    
    public static void main(String[] args) throws ParseException{
        HibernateOracle pruebahibernate = new HibernateOracle();
        
        /**
         * Aprovechamos que Hibernate soporta NO incluir el SELECT
         * para acortar la consulta HQL.
         * Obtenemos los departamentos.
         */
        List<Dept> deps = pruebahibernate.consulta("FROM Dept");
        Dept departament = deps.get(0);
        
        /**
         * Visto el tipo de datos que se utilizan para guardar 
         * cada campo, genero las siguientes variables.
         * @param short numemp recoge el número de empleado
         * @param short numdep recoge el número de departamento 
         * al que es asignado.
         * @param String nombre recoge nombre empleado (la BD 
         * facilitada sólo ofrece un tamaño de 10 bytes).
         * @param String puesto recoge cargo o puesto del empleado (la BD 
         * facilitada sólo ofrece un tamaño de 9 bytes).
         * @param String valorFecha recoge la fecha de entrada (atención
         * con el formato que nos permite: yyyy-mm-dd ).
         * @param fecha objeto Date que recoge el string anterior.
         * @param BigDecimal salario recoge el valor entero del sueldo
         * @param BigDecimal comision recoge el valor entero de comisión.
         * 
         */

        short numemp = 7115;
        short numdep = 7788;
        String nombre ="PABLO";
        String puesto ="ANALYST";       
        String valorFecha = "2022-01-21";
        //recordar el tipo de formato para fechas
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = sdf.parse(valorFecha);
        BigDecimal salario = BigDecimal.valueOf(1250);
        BigDecimal comision = BigDecimal.valueOf(0);//por registrar un valor
        
        Emp datosEmpleado = new Emp(numemp, departament, nombre, puesto,
            numdep, fecha, salario, comision);

        pruebahibernate.insertarEmpl(datosEmpleado);
        pruebahibernate.borrarEmpl(datosEmpleado);
        
        
        /**
         * Obtenemos la lista de las propiedades solicitadas en el 
         * enunciado siguiendo las pautas del temario, mediante una 
         * Lista de Array de Objetos
         */
        List<Object[]> lista = pruebahibernate.consulta("FROM Emp as empleado INNER JOIN empleado.dept as departamento");
        for (Object[] objeto : lista) {
            Emp empleado = (Emp) objeto[0];
            Dept departamento = (Dept) objeto[1];
            System.out.println("NUMERO: " + empleado.getEmpno()+ " \t NOMBRE: " + empleado.getEname()
                    + "\t SUELDO: " + empleado.getSal() + "\t LOCALIDAD: " + departamento.getLoc()
                    + "\t DEPARTAMENTO: " + departamento.getDname());
        }
    }//fin main
    
}//fin clase HibernateOracle
