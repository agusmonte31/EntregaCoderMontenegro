package com.desafioapirest.cliente.service.clientes;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Clientes;
import com.desafioapirest.cliente.dto.ClientesDTO;
import com.desafioapirest.cliente.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServImpl implements ClienteService{
    @Autowired
    ClientesRepository clientesRepository;
    List<Clientes> clientescopia;



    int finalLista, anio,mes,dia;
    Clientes elementocliente;
    ClientesDTO amostrar;
    Integer edad;
    Date fechanacimiento;
    String fechanacimientoString;



    @Override
    public List<Clientes> mostrarTodos() {
        return clientesRepository.findAll();
    }

    @Override
    public List<ClientesDTO> mostrarClientesEdad() {
        List<ClientesDTO> listaParaMostrar = new ArrayList<>();
        clientescopia=clientesRepository.findAll();
        finalLista=clientescopia.size();
        for(int i=0; i<=finalLista-1;i++){
           elementocliente=clientescopia.get(i);
           edad = calcularEdad(elementocliente);
           amostrar= new ClientesDTO(elementocliente.getIdcliente(),elementocliente.getDni(), elementocliente.getNombre(), elementocliente.getApellido(), edad);
           listaParaMostrar.add(amostrar);
        }
        return listaParaMostrar;
    }

    @Override
    public ClientesDTO mostrarByDNI(int dni) throws Exception{
        clientescopia=clientesRepository.findAll();
        for(int i =0;i<clientescopia.size();i++){
            if(clientescopia.get(i).getDni().equals(dni)){
                elementocliente=clientescopia.get(i);
                edad = calcularEdad(elementocliente);
                amostrar= new ClientesDTO(elementocliente.getIdcliente(),elementocliente.getDni(), elementocliente.getNombre(), elementocliente.getApellido(), edad);
                return amostrar;
            }
        }
        throw new ApiException("No se encuentra el DNI : "+dni);
    }

    @Override
    public Clientes mostrarOriginalByID(Integer idcliente) throws Exception {
        elementocliente= clientesRepository.findById(idcliente).orElse(null);
        if(elementocliente==null){throw new ApiException("No se encontro ningun cliente con el ID :"+idcliente);}
        return elementocliente;
    }

    @Override
    public ClientesDTO mostrarEdadByID(Integer idcliente) throws Exception {
        elementocliente=clientesRepository.findById(idcliente).orElse(null);
        if(elementocliente==null){
            throw new ApiException("No se encontro ningun cliente con el ID :"+idcliente);
        }
        edad=calcularEdad(elementocliente);
        amostrar= new ClientesDTO(elementocliente.getIdcliente(), elementocliente.getDni(), elementocliente.getNombre(), elementocliente.getApellido(), edad);
        return amostrar;
    }



    public ClientesDTO nuevoCliente(Clientes cliente) throws Exception {
        boolean dniRepetido = buscarDniRepetido(cliente);
        if(dniRepetido){
            throw new ApiException("El DNI ya se encuentra en nuestra base de datos");
        }else {
            int id = calcularID();
            cliente.setIdcliente(id);
            clientesRepository.save(cliente);
            edad = calcularEdad(cliente);
            amostrar = new ClientesDTO(id, cliente.getDni(), cliente.getNombre(), cliente.getApellido(), edad);
            return (amostrar);
        }
    }

    @Override
    public ClientesDTO actualizarCliente(Clientes cliente) throws Exception{
        clientescopia = clientesRepository.findAll();
        finalLista = clientescopia.size();
        if(cliente.getIdcliente()<=finalLista && cliente.getIdcliente()>0){
            if(buscarDniRepetido(cliente)){throw new ApiException("El DNI ingresado pertenece a otro cliente existente");}
            clientesRepository.save(cliente);
            edad=calcularEdad(cliente);
            amostrar = new ClientesDTO(cliente.getIdcliente(), cliente.getDni(), cliente.getNombre(), cliente.getApellido(), edad);
          return amostrar;
        }
        throw new ApiException("ID Cliente inexistente");
    }



    @Override
    public String borrarCliente(int id) {
        clientescopia= clientesRepository.findAll();
        String texto = "No se encontró el cliente con el id: "+id ;
        if(id<1){return texto;}
        for(int i=0;i<clientescopia.size();i++){
            if(id==clientescopia.get(i).getIdcliente()) {
                clientesRepository.deleteById(id);
                texto = "Cliente con id: " + id + "eliminiado";
                i=clientescopia.size();
            }
        }
        return texto;
    }


    @Override
    public boolean buscarIDCliente(int idcliente) {
        clientescopia=clientesRepository.findAll();
        for(int i=0;i<clientescopia.size();i++){
            if(clientescopia.get(i).getIdcliente()==idcliente) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getNombreApellido(int idcliente) {
        elementocliente = clientesRepository.findById(idcliente).orElse(null);
        return elementocliente.getNombre()+" "+elementocliente.getApellido();
    }


    private int calcularEdad (Clientes cliente){
        fechanacimiento=cliente.getFechanacimiento();
        fechanacimientoString=fechanacimiento.toString();
        anio=Integer.parseInt(fechanacimientoString.substring(0,4));
        mes= Integer.parseInt(fechanacimientoString.substring(5,7));
        dia= Integer.parseInt(fechanacimientoString.substring(8,10));
        edad=Period.between(LocalDate.of(anio,mes,dia),LocalDate.now()).getYears();
        return edad;
    }

    private boolean buscarDniRepetido(Clientes cliente){
        clientescopia=clientesRepository.findAll();
        for(int i=0;i<clientescopia.size();i++){
            elementocliente=clientescopia.get(i);
            if(elementocliente.getDni().equals(cliente.getDni())) {
                return true;
            }
        }
        return false;
    }

    private int calcularID(){
        return clientesRepository.findAll().size()+1;
    }

}
