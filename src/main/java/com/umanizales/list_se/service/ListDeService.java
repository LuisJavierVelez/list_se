package com.umanizales.list_se.service;

import com.umanizales.list_se.controller.dto.ResponseDTO;
import com.umanizales.list_se.exception.ListaDeException;
import com.umanizales.list_se.model.*;
import com.umanizales.list_se.model.listade.ListaDE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListDeService {

    private ListaDE listBoys;
    private List<Location> locations;
    private List<Gender> genders;
    private Object head1;

    public ListDeService(){
        listBoys = new ListaDE();
        initializeLocations();
        initializeGenders();
    }
    public void initializeLocations()
    {
        locations = new ArrayList<>();
        locations.add(new Location("16917001","Manizales"));
        locations.add(new Location("16917003","Chinchina"));
        locations.add(new Location("16917004","Villamaria"));
        locations.add(new Location("16917005","Neira"));

    }
    public void initializeGenders()
    {
        genders = new ArrayList<>();
        genders.add(new Gender("MASCULINO"));
        genders.add(new Gender("FEMENINO"));
    }

    public boolean validateLocationDe(Location location)

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

    public ResponseEntity<ResponseDTO> addBoyDe(Boy boy) throws ListaDeException {
        if(!validateLocationDe(boy.getLocation())){
            throw new ListaDeException("la ubicacion ingresada no es valida");
        }
        if (listBoys.addDe(boy)) {
            return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO("Niño ya esta adicionado", true, null),
                HttpStatus.OK);

    }

    public ResponseEntity<ResponseDTO> addBoyByPositionDe(Boy boy, int position) throws ListaDeException {
        listBoys.addBoyByPositionDe(boy, position);
        return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);


    }

   /*public static ResponseEntity<ResponseDTO> addBoyToStartDe(Boy boy) throws ListaDeException {
        listBoys.addToStartDe(boy);
        return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public static ResponseEntity<ResponseDTO> listBoysDe()throws ListaDeException {

        if(listBoys.getHead1()== null)
        {
            throw new ListaDeException("No hay datos en la Lista");
        }
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.getHead1(), null),
                HttpStatus.OK);

    }*/

    public ResponseEntity<ResponseDTO> listBoysFreesDe() throws ListaDeException {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.listDe(), null),
                HttpStatus.OK);

    }

    public ResponseEntity<ResponseDTO> invertlistDe() throws ListaDeException {
        listBoys.invertDe();
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.list, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> getCountDe() {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.getCount(), null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> countDe() {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.countDe(), null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> changeXtremesDe() throws ListaDeException {
        listBoys.changeXtremesDe();
        return new ResponseEntity<>(new ResponseDTO("Satiosfactorio", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> deletBoyDe(String id) throws ListaDeException {
        listBoys.deletBoyDe(id);
        return new ResponseEntity<>(new ResponseDTO("Eliminado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> GenderDe(String gender) throws ListaDeException {
        return new ResponseEntity<>(new ResponseDTO("Lista Genero", listBoys.listDe().stream().filter(boy -> boy.getGender().equals(gender)), null), HttpStatus.OK);

    }



    public ResponseEntity<ResponseDTO> positionDe(String id) throws ListaDeException {
        return new ResponseEntity<>(new ResponseDTO("Posicion", listBoys.listDe().stream().filter(boy -> boy.getGender().equals(id)), null), HttpStatus.OK);

    }



    public ResponseEntity<ResponseDTO> chagepositionDe(String id, int num) throws ListaDeException {
        if (listBoys.changePositionDe(id, num)) {
            return new ResponseEntity<>(new ResponseDTO("Niño pierde " +num+ " posiciones", true, null), HttpStatus.OK);

        }
        return new ResponseEntity<>(new ResponseDTO("No es posible perder posiciones", false,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> variantlistDe() throws ListaDeException {
        listBoys.variantBoysDe();
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.getHead1(), null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> listlocationbyage(byte age, String location) throws ListaDeException {
        listBoys.listLocationByAge(age, location);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.listLocationByAge(age, location), null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> getBoysByLocationDe() throws ListaDeException
    {
        List<BoysByLocation> boysByLocations = new ArrayList<>();
        for(Location loc: locations)
        {
            int count  = listBoys.getCountBoyByLocationDe(loc.getCode());
            boysByLocations.add(new BoysByLocation(loc,count));

        }
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio",boysByLocations,null),HttpStatus.OK);

    }
    public ResponseEntity<ResponseDTO> listbygenerageDe(byte age, String gender) throws ListaDeException {
        listBoys.ListByGenderAgeDe(age, gender);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", true, null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> deleteboybyageDe(byte age) throws ListaDeException {
        listBoys.DeleteBoyByAgeDe(age);
        return new ResponseEntity<>(
                new ResponseDTO("Niño Eliminado", true, null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> deleteboybygenderDe(String gender) throws ListaDeException {
        listBoys.DeleteBoyByGenderDe(gender);
        return new ResponseEntity<>(
                new ResponseDTO("Niño Eliminado", true, null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> deleteboypositionDe(int position) throws ListaDeException {
        listBoys.DeleteBoyPositionDe(position);
        return new ResponseEntity<>(new ResponseDTO("Niño Eliminado", true, null),
                HttpStatus.OK);


    }
    public ResponseEntity<ResponseDTO> listboydegreeDe(Integer degree) throws ListaDeException {
        listBoys.listBoyDegreeDe(degree);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.listBoyDegreeDe(degree), null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> invertboybygenderageDe(String gender, byte age) throws ListaDeException {
        listBoys.invertBoyByGenderAgeDe(gender, age);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", true, null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> getBoysByGenderDe()
    {
        List<BoysByGender> boysByGender = new ArrayList<>();
        for(Gender gen: genders)
        {
            int count  = listBoys.getCountBoyByGenderDe(gen.getDescription());
            boysByGender.add(new BoysByGender(gen,count));

        }
        return new ResponseEntity<>(new ResponseDTO("SatisFactori",boysByGender,null),HttpStatus.OK);

    }
    public ResponseEntity<ResponseDTO>  gendersByGradeByLocation() throws ListaDeException
    {
        List<GenderByLocationDTO> genderByLocationDTOS = new ArrayList<>();
        for (Location loc: locations)
        {
            genderByLocationDTOS.add(listBoys.getGenderByLocation(loc));
        }

        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", genderByLocationDTOS, null), HttpStatus.OK);
    }

    /**
     * Cargamos la respuesta del servicio
     * @return retronamos la lista temporal
     * @throws ListaDeException
     */
    public ResponseEntity<ResponseDTO> orderlocation() throws ListaDeException {
        /**
         * creamos una lista temportal con la datos de la lista doblemente enlazada
         */
        ListaDE listTemp = new ListaDE();
        /**
         * Recorremos la lista por ubicacion ingresada
         */
        for(Location loc: locations)
        {
            /**
             * Creamos uan lista donde ingresamos la informacion nodo
             */
            ListaDE lisloc = this.listBoys.listaDELocations(loc);
            /**
             * indico que la lista temporal de ubicacion es la cabeza sea diferente a null
             */
            if(lisloc.getHead1()!= null)

            {
                /**
                 * Le indico a lista temporal que agarre la cabeza
                 */
                listTemp.addNode(lisloc.getHead1());
            }
        }
        /**
         * Retornamos la lista temporal.
         */
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listTemp.listDe(), null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>  getGenderByLocation() throws ListaDeException
    {
        List<GenderByLocationDTO> genderByLocationDTOS = new ArrayList<>();
        for (Location loc: locations)
        {
            genderByLocationDTOS.add(listBoys.getGenderByLocation(loc));
        }

        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", genderByLocationDTOS, null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO>  getOrphansByGradeByLocation() throws ListaDeException
    {
        List<GradesByLocationDTO> gradeByLocationDTOS = new ArrayList<>();
        for (Location loc: locations)
        {
            gradeByLocationDTOS.add(listBoys.getGradesByLocation(loc));
        }

        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", gradeByLocationDTOS, null), HttpStatus.OK);
    }
}

