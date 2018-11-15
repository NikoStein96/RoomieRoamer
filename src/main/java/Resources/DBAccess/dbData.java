/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources.DBAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tarllark
 */
public class dbData
{
    public String getData(int offset) throws SQLException, ClassNotFoundException {
            List<String> data = new ArrayList<>();
            Connection con = Connector.connection();
            String SQL = "select * from CA3.dummy  ORDER BY id ASC LIMIT 20 OFFSET ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, (offset));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data.add(rs.getString("desc"));
            }
            String res = "";
            for(String d : data){
                res += d + "\n";
            }
            return res;
        
    }
}
