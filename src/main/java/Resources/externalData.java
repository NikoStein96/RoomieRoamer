/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

/**
 *
 * @author Tarllark
 */
public class externalData
{
    
    public String getAll()
    {
        String result = "";
        getData tc1 = new getData("https://swapi.co/api/people/");
        result += tc1.call();
        getData tc2 = new getData("https://swapi.co/api/starships/");
        result += tc2.call();
        getData tc3 = new getData("https://swapi.co/api/planets/");
        result += tc3.call();
        getData tc4 = new getData("https://swapi.co/api/vehicles/");
        result += tc4.call();
        getData tc5 = new getData("https://swapi.co/api/species/");
        result += tc5.call();
        getData tc6 = new getData("https://swapi.co/api/films/");
        result += tc6.call();
        
        return result;
    }


    

}
