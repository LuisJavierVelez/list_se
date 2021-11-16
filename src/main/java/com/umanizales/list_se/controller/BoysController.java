package com.umanizales.list_se.controller;

import com.umanizales.list_se.controller.dto.ResponseDTO;
import com.umanizales.list_se.exception.ListaDeException;
import com.umanizales.list_se.exception.ListaSeException;
import com.umanizales.list_se.model.Boy;
import com.umanizales.list_se.service.ListDeService;
import com.umanizales.list_se.service.ListSeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador esta clase se encarga de gestionar los get y post de cada metodo y respuestas del servicio los cuelas devuelven los valores
 * de las propiedades o elementos indicados, en via la informacion por url,en algunos get se envia por parametro los datos solicitados por los metodos para que se
 * ejecuten
 * @author Luis Javier Velez Uribe
 * @version 1.0 05-11-2021
 */

@RestController
@RequestMapping(path = "boys")
public class BoysController {
    @Autowired
    private ListSeService listSeService;
    @Autowired
    private ListDeService listDeService;


    @PostMapping
    public ResponseEntity<ResponseDTO> addBoys(@RequestBody @Valid Boy boy) throws ListaSeException
    {
        return listSeService.addBoy(boy);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> listBoys() throws ListaSeException
    {
        return listSeService.listBoys();
    }
    @GetMapping(path = "free")
    public ResponseEntity<ResponseDTO> listBoysFree() throws ListaSeException
    {

        return listSeService.listBoysFrees();
    }

    @GetMapping(path = "invert")
    public ResponseEntity<ResponseDTO> invertList() throws ListaSeException {
        return listSeService.invertlist();
    }

    @PostMapping(path="addtoposition/{position}")
    public ResponseEntity<ResponseDTO> addBoyByPosition(@PathVariable int position, @RequestBody Boy boy) throws ListaSeException
    {
        return listSeService.addBoyByPosition(boy,position);
    }

    @PostMapping(path="addBoyToStart")
    public ResponseEntity<ResponseDTO> addBoyToStart(@RequestBody Boy boy) throws ListaSeException
    {
        return listSeService.addBoyToStart(boy);
    }

    @PostMapping(path="addboys")
    public ResponseEntity<ResponseDTO> addBoys(@RequestBody List<Boy>  boys) throws ListaSeException
    {
        for(Boy boy:boys)
        {
            listSeService.addBoy(boy);
        }
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Satisfactorio",listSeService.listBoys(), null), HttpStatus.OK);
    }

    @GetMapping(path="getcount")
    public ResponseEntity<ResponseDTO> getCount()
    {
        return listSeService.getCount();
    }
    @GetMapping(path="count")
    public ResponseEntity<ResponseDTO> count()
    {
        return listSeService.count();
    }
    @GetMapping(path="changextremes")
    public ResponseEntity<ResponseDTO> changeXtremes() throws ListaSeException
    {
        return listSeService.changeXtremes();
    }

    @GetMapping(path = "/list/{gender}")
    public  ResponseEntity<ResponseDTO> Gender(@PathVariable String gender) throws ListaSeException { return listSeService.Gender(gender);}
    /*@GetMapping(path = "/listgender/{gender}")
    public  ResponseEntity<ResponseDTO> ListGender(@PathVariable String gender) throws ListaSeException { return listSeService.ListGender(gender);}*/
    @GetMapping(path = "/delet/{id}")
    public  ResponseEntity<ResponseDTO> deletBoy(@PathVariable String id) throws ListaSeException { return listSeService.deletBoy(id);}
    @GetMapping(path = "/list/{id}")
    public  ResponseEntity<ResponseDTO> position(@PathVariable String id) throws ListaSeException { return listSeService.position(id);}
    @GetMapping(path="/position/{id}/{num}")
    public ResponseEntity<ResponseDTO> changePosition(@PathVariable String id, @PathVariable int num) throws ListaSeException {
        return  listSeService.chageposition(id,num);
    }
    @GetMapping(path = "variant")
    public ResponseEntity<ResponseDTO> variantlist() throws ListaSeException {
        return listSeService.variantlist();
    }
    @GetMapping(path = "boysbylocation")
    public ResponseEntity<ResponseDTO> boysByLocation() {
        return listSeService.getBoysByLocation();
    }

    @GetMapping(path="listlocationbyage/{age}/{location}")
    public ResponseEntity<ResponseDTO> listlocationbyage(@PathVariable byte age , @PathVariable String location) throws ListaSeException
    {
        return listSeService.listlocationbyage(age,location);
    }
    @GetMapping(path="listbygenderage/{age}/{gender}")
    public ResponseEntity<ResponseDTO> listbygenderage(@PathVariable byte age , @PathVariable String gender) throws ListaSeException
    {
        return listSeService.listbygenderage(age,gender);
    }
    @GetMapping(path="deleteboybyage/{age}")
    public ResponseEntity<ResponseDTO> deleteboybyage(@PathVariable byte age) throws ListaSeException
    {
        return listSeService.deleteboybyage(age);
    }
    @GetMapping(path="deleteboybygender/{gender}")
    public ResponseEntity<ResponseDTO> deleteboybygender(@PathVariable String gender) throws ListaSeException
    {
        return listSeService.deleteboybygender(gender);
    }
    @GetMapping(path="deleteboyposition/{position}")
    public ResponseEntity<ResponseDTO> deleteboyposition(@PathVariable int position) throws ListaSeException
    {
        return listSeService.deleteboyposition(position);
    }
    @GetMapping(path="listboydegree/{degree}")
    public ResponseEntity<ResponseDTO> listboydegree(@PathVariable Integer degree) throws ListaSeException
    {
        return listSeService.listboydegree(degree);
    }
    @GetMapping(path = "invertboybygenderage/{gender}/{age}")
    public ResponseEntity<ResponseDTO> invertboybygenderage(@PathVariable String gender, @PathVariable byte age) throws ListaSeException {
        return listSeService.invertboybygenderage(gender, age);
    }
    @GetMapping(path = "boysbygender")
    public ResponseEntity<ResponseDTO> boysByGender() {
        return listSeService.getBoysByGender();
    }



    /////////////////////////////Lista DE ///////////////////////////////////////

    @PostMapping(path = "addboyde")
    public ResponseEntity<ResponseDTO> AddBoysDe(@RequestBody @Valid Boy boy)throws ListaDeException{
        return listDeService.addBoyDe(boy);
    }
    @GetMapping(path = "listde")
    public ResponseEntity<ResponseDTO>listBoyDe() throws ListaDeException{
        return listDeService.listBoysFreesDe();
    }

    @GetMapping(path = "deletede/{id}")
    public ResponseEntity<ResponseDTO>deleteBoyDe(@PathVariable String id)throws ListaDeException{
        return listDeService.deletBoyDe(id);
    }

    @GetMapping(path = "deleteboysde")
    public ResponseEntity<ResponseDTO> deletBoyDe(@PathVariable String id)throws ListaDeException{
        return listDeService.deletBoyDe(id);
    }
    @GetMapping(path = "freede")
    public ResponseEntity<ResponseDTO> listBoysFreeDe() throws ListaDeException
    {

        return listDeService.listBoysFreesDe();
    }

    @GetMapping(path = "invertde")
    public ResponseEntity<ResponseDTO> invertListDe() throws ListaDeException {
        return listDeService.invertlistDe();
    }

    @PostMapping(path="addtopositionde/{position}")
    public ResponseEntity<ResponseDTO> addBoyByPositionDe(@PathVariable int position, @RequestBody Boy boy) throws ListaDeException
    {
        return listDeService.addBoyByPositionDe(boy,position);
    }



    @PostMapping(path="addboysde")
    public ResponseEntity<ResponseDTO> addBoysDe(@RequestBody List<Boy>  boys) throws ListaDeException
    {
        for(Boy boy:boys)
        {
            listDeService.addBoyDe(boy);
        }
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio",listDeService.listBoysDe(), null), HttpStatus.OK);
    }

    @GetMapping(path="getcountde")
    public ResponseEntity<ResponseDTO> getCountDe()
    {
        return listDeService.getCountDe();
    }
    @GetMapping(path="countde")
    public ResponseEntity<ResponseDTO> countDe()
    {
        return listDeService.countDe();
    }
    @GetMapping(path="changextremesde")
    public ResponseEntity<ResponseDTO> changeXtremesDe() throws ListaDeException
    {
        return listDeService.changeXtremesDe();
    }

    @GetMapping(path = "/listde/{gender}")
    public  ResponseEntity<ResponseDTO> GenderDe(@PathVariable String gender) throws  ListaDeException { return listDeService.GenderDe(gender);}
    /*@GetMapping(path = "/listgender/{gender}")
    public  ResponseEntity<ResponseDTO> ListGender(@PathVariable String gender) throws ListaSeException { return listSeService.ListGender(gender);}*/


    @GetMapping(path="/positionde/{id}/{num}")
    public ResponseEntity<ResponseDTO> changePositionDe(@PathVariable String id, @PathVariable int num) throws ListaDeException {
        return  listDeService.chagepositionDe(id,num);
    }
    @GetMapping(path = "variantde")
    public ResponseEntity<ResponseDTO> variantlistDe() throws ListaDeException {
        return listDeService.variantlistDe();
    }
    @GetMapping(path = "boysbylocationde")
    public ResponseEntity<ResponseDTO> boysByLocationDe() throws ListaDeException {
        return listDeService.getBoysByLocationDe();
    }

    @GetMapping(path="listlocationbyagede/{age}/{location}")
    public ResponseEntity<ResponseDTO> listlocationbyageDe(@PathVariable byte age , @PathVariable String location) throws  ListaDeException {
        return listDeService.listlocationbyage(age,location);
    }

    @GetMapping(path="deleteboybyagede/{age}")
    public ResponseEntity<ResponseDTO> deleteboybyageDe(@PathVariable byte age) throws ListaDeException
    {
        return listDeService.deleteboybyageDe(age);
    }
    @GetMapping(path="deleteboybygenderde/{gender}")
    public ResponseEntity<ResponseDTO> deleteboybygenderDe(@PathVariable String gender) throws ListaDeException
    {
        return listDeService.deleteboybygenderDe(gender);
    }
    @GetMapping(path="deleteboypositionde/{position}")
    public ResponseEntity<ResponseDTO> deleteboypositionDe(@PathVariable int position) throws ListaDeException
    {
        return listDeService.deleteboypositionDe(position);
    }
    @GetMapping(path="listboydegreede/{degree}")
    public ResponseEntity<ResponseDTO> listboydegreeDe(@PathVariable Integer degree) throws ListaDeException
    {
        return listDeService.listboydegreeDe(degree);
    }
    @GetMapping(path = "invertboybygenderagede/{gender}/{age}")
    public ResponseEntity<ResponseDTO> invertboybygenderageDe(@PathVariable String gender, @PathVariable byte age) throws ListaDeException {
        return listDeService.invertboybygenderageDe(gender, age);
    }
    @GetMapping(path = "boysbygenderde")
    public ResponseEntity<ResponseDTO> boysByGenderDe() {
        return listDeService.getBoysByGenderDe();
    }

    @GetMapping(path = "orderLocation")
    public ResponseEntity<ResponseDTO> orderLocation() throws ListaDeException
    { return listDeService.orderlocation();}

    public ResponseEntity<ResponseDTO> boysByGenderorphanDe() throws ListaDeException
    {return listDeService.getOrphansByGradeByLocation();}


    @GetMapping(path = "boysbygenderlocation")
    public ResponseEntity<ResponseDTO> getGenderByLocation() throws ListaDeException
    {return listDeService.getGenderByLocation();}

    @GetMapping(path = "getorderboyandgirl")
    public ResponseEntity<ResponseDTO> getOrderBoyAndGirl()throws ListaDeException{
        return listDeService.getOrderBoyAndGirl();
    }

}
