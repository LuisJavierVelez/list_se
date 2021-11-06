package com.umanizales.list_se.service;

import com.umanizales.list_se.controller.dto.ResponseDTO;
import com.umanizales.list_se.exception.ListaSeException;
import com.umanizales.list_se.model.*;
import com.umanizales.list_se.model.listase.ListSE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio Lista SE en esta clase se encuentra registrados los servicios relacionados a cada metodo creado en la clase ListSE se encarga de gestionar
 * los datos y mensajes de tipo de DTO y dar respuesta a los distintos requerimientos que se generan desde los metodos, a cada servicio le llengan los
 * paramentro indicados y el retorna las respuestas sobre los metodos
 * @author Luis Javier Velez Uribe
 * @version  1.0 05-11-2021
 */
@Service
public class ListSeService {

    private ListSE listBoys;
    private List<Location> locations;
    private List<Gender> genders;

    public ListSeService() {
        listBoys = new ListSE();
        initializeLocations();
        initializeGenders();
    }

    public void initializeLocations()
    {
        locations = new ArrayList<>();
        locations.add(new Location("16917001","Manizales"));
        locations.add(new Location("16917003","Chinchina"));
    }
    public void initializeGenders()
    {
        genders = new ArrayList<>();
        genders.add(new Gender("MASCULINO"));
        genders.add(new Gender("FEMENINO"));
    }

    public boolean validateLocation(Location location)

    {
        for(Location loc: locations)
        {
            if(loc.getCode().equals(location.getCode())&& loc.getDescription().equals(location.getDescription()))
            {
                return  true;
            }
        }
        return false;
    }

    public ResponseEntity<ResponseDTO> addBoy(Boy boy) throws ListaSeException {
        if(!validateLocation(boy.getLocation())){
            throw new ListaSeException("la ubicacion ingresada no es valida");
        }
        if (listBoys.add(boy)) {
            return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO("Niño ya esta adicionado", true, null),
                HttpStatus.OK);

    }

    public ResponseEntity<ResponseDTO> addBoyByPosition(Boy boy, int position) throws ListaSeException {
        listBoys.addBoyByPosition(boy, position);
            return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null),
                    HttpStatus.OK);


    }

    public ResponseEntity<ResponseDTO> addBoyToStart(Boy boy) throws ListaSeException {
        listBoys.addBoyToStart(boy);
        return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> listBoys()throws ListaSeException {

        if(listBoys.getHead()== null)
        {
            throw new ListaSeException("No hay datos en la Lista");
        }
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.getHead(), null),
                HttpStatus.OK);

    }

    public ResponseEntity<ResponseDTO> listBoysFrees() throws ListaSeException {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.list(), null),
                HttpStatus.OK);

    }

    public ResponseEntity<ResponseDTO> invertlist() throws ListaSeException {
        listBoys.invert();
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.getHead(), null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> getCount() {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.getCount(), null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> count() {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.count(), null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> changeXtremes() throws ListaSeException {
        listBoys.changeXtremes();
        return new ResponseEntity<>(new ResponseDTO("Satiosfactorio", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> deletBoy(String id) throws ListaSeException {
        listBoys.deletBoy(id);
        return new ResponseEntity<>(new ResponseDTO("Eliminado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> Gender(String gender) throws ListaSeException {
        return new ResponseEntity<>(new ResponseDTO("Lista Genero", listBoys.list().stream().filter(boy -> boy.getGender().equals(gender)), null), HttpStatus.OK);

    }



    public ResponseEntity<ResponseDTO> position(String id) throws ListaSeException {
        return new ResponseEntity<>(new ResponseDTO("Posicion", listBoys.list().stream().filter(boy -> boy.getGender().equals(id)), null), HttpStatus.OK);

    }



    public ResponseEntity<ResponseDTO> chageposition(String id, int num) throws ListaSeException {
        if (listBoys.changePosition(id, num)) {
            return new ResponseEntity<>(new ResponseDTO("Niño pierde " +num+ " posiciones", true, null), HttpStatus.OK);

        }
        return new ResponseEntity<>(new ResponseDTO("No es posible perder posiciones", false,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> variantlist() throws ListaSeException {
        listBoys.variantBoys();
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.getHead(), null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> listlocationbyage(byte age, String location) throws ListaSeException {
        listBoys.listLocationByAge(age, location);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.listLocationByAge(age, location), null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> getBoysByLocation()
    {
        List<BoysByLocation> boysByLocations = new ArrayList<>();
        for(Location loc: locations)
        {
            int count  = listBoys.getCountBoyByLocation(loc.getCode());
            boysByLocations.add(new BoysByLocation(loc,count));

        }
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio",boysByLocations,null),HttpStatus.OK);

    }
    public ResponseEntity<ResponseDTO> listbygenderage(byte age, String gender) throws ListaSeException {
        listBoys.ListByGenderAge(age, gender);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", true, null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> deleteboybyage(byte age) throws ListaSeException {
        listBoys.DeleteBoyByAge(age);
        return new ResponseEntity<>(
                new ResponseDTO("Niño Eliminado", true, null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> deleteboybygender(String gender) throws ListaSeException {
        listBoys.DeleteBoyByGender(gender);
        return new ResponseEntity<>(
                new ResponseDTO("Niño Eliminado", true, null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> deleteboyposition(int position) throws ListaSeException {
        listBoys.DeleteBoyPosition(position);
        return new ResponseEntity<>(new ResponseDTO("Niño Eliminado", true, null),
                HttpStatus.OK);


    }
    public ResponseEntity<ResponseDTO> listboydegree(Integer degree) throws ListaSeException {
        listBoys.listBoyDegree(degree);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.listBoyDegree(degree), null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> invertboybygenderage(String gender, byte age) throws ListaSeException {
        listBoys.invertBoyByGenderAge(gender, age);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", true, null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> getBoysByGender()
    {
        List<BoysByGender> boysByGender = new ArrayList<>();
        for(Gender gen: genders)
        {
            int count  = listBoys.getCountBoyByGender(gen.getDescription());
            boysByGender.add(new BoysByGender(gen,count));

        }
        return new ResponseEntity<>(new ResponseDTO("SatisFactori",boysByGender,null),HttpStatus.OK);

    }


}


















