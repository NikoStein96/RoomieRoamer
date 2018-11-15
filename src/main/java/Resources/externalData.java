/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 *
 * @author Tarllark
 */
public class externalData
{
    
    public String getAll()
    {
        JSONArray JA = new JSONArray();
        List<String> results = new ArrayList();
        getData tc1 = new getData("https://swapi.co/api/people/1/");
        results.add(tc1.call());
        getData tc2 = new getData("https://swapi.co/api/people/2/");
        results.add(tc2.call());
        getData tc3 = new getData("https://swapi.co/api/people/3/");
        results.add(tc3.call());
        getData tc4 = new getData("https://swapi.co/api/people/4/");
        results.add(tc4.call());
        getData tc5 = new getData("https://swapi.co/api/people/5/");
        results.add(tc5.call());
        
        for(String o : results)
            JA.add(o);
        JSONObject res = new JSONObject();
        res.put("Results", JA);
        return res.toJSONString();
    }


    

}
