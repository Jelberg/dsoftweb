package M05;

import M05.Model.Global;
import M05.Model.Sport;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;



/**
 * Created by estefania on 14/05/2017.
 */
@Path("/M05.EjemploDB")
public class SportController {

    Gson gson = new Gson();


    @GET

    @Path("/insertarDeporte")

    @Produces("application/json")

    public String insertarSport(@QueryParam("nombre") String nombre){

        String query="select public.insertarDeporte(1 :: INTEGER , 1 :: INTEGER) " ;

        try{
            Connection conn=conectarADb();
            Statement st=conn.createStatement();
            st.executeUpdate(query);
           return gson.toJson(true);

            } catch (Exception e) {
           return  e.getMessage();
        }

    }


    @GET

    @Path("/ObtenerDeporte")

    @Produces("application/json")

    public String obtenerSport(@QueryParam("nombre") String name){


       String query = "select public.obtenerDeporte('"+name.toUpperCase()+"' :: VARCHAR(200));";

       Sport resultado= new Sport();

        try{
            Connection conn=conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){

                resultado.setId(rs.getInt("sportid"));
                resultado.setName(rs.getString("sportname"));
                resultado.setMet(rs.getFloat("sportmet"));


            }
            return gson.toJson(resultado);

        } catch (Exception e) {
           return e.getMessage();
        }
    }

    public float obtenerMet( String name){


        String query = "SELECT SPORTMET FROM sport WHERE sportname='"+name+"'";

        Sport resultado= new Sport();

        try{
            Connection conn=conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){

                resultado.setMet(rs.getFloat("sportmet"));
            }
            return resultado.getMet();

        } catch (Exception e) {
            return 0;
        }
    }

    private Connection conectarADb(){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:"+ Global.port+"/"+Global.nameBd;
            conn       = DriverManager.getConnection(url, "postgres",  "postgres");



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return conn;
    }

}
