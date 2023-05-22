package org.tabajara.resource;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tabajara.controller.FuncionarioController;
import org.tabajara.entity.Funcionario;


import java.util.List;

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {
    private FuncionarioController funcionarioService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        return Response.ok(funcionarios).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterFuncionarioPorId(@PathParam("id") long id) {
        Funcionario funcionario = funcionarioService.obterFuncionarioPorId(id);
        if (funcionario != null) {
            return Response.ok(funcionario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarFuncionario(Funcionario funcionario) {
        funcionarioService.criarFuncionario(funcionario);
        return Response.status(Response.Status.CREATED).entity(funcionario).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarFuncionario(@PathParam("id") long id, Funcionario funcionario) {
        funcionario.setId(id);
        Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionario(funcionario);
        if (funcionarioAtualizado != null) {
            return Response.ok(funcionarioAtualizado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirFuncionario(@PathParam("id") long id) {
        boolean removido = funcionarioService.excluirFuncionario(id);
        if (removido) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}