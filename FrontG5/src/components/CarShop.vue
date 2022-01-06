<template>
    <div class="container_venta">
        <div class="list_products">
            <h1 class="titleProducts">PRODUCTOS</h1>
             <form v-on:submit.prevent="processVentas" >
                <input type="number" v-model="createVentas.cantidad" placeholder="Cantidad">
                <br>
                
                <input type="number" v-model="precio" placeholder="Precio Unidad">
                <br>
                <h3> TOTAL:</h3>
                <h3>${{createVentas.preciototal}}COP</h3>
                
                <button class="B_realizarCompra" type="submit" @click="sumar">Realizar </button>
            </form>
        </div>
    </div>
</template>
<script>
import gql from "graphql-tag";
export default {
    name: "CarShop",
    data: function(){
        
        return{
            precio:"",
            total:"",
            createVentas:{
                username: localStorage.getItem("username"),
                cantidad: "",
                preciototal: ""
            }
        }
    },
    methods:{
            processVentas: async function() {
            if (
                localStorage.getItem("token_access") === null ||
                localStorage.getItem("token_refresh") === null
            ) {
                this.$emit("logOut");
                return;
            }
            localStorage.setItem("token_access", "");
            await this.$apollo
                .mutate({
                    mutation: gql`
                        mutation($refresh: String!) {
                            refreshToken(refresh: $refresh) {
                                access
                            }
                        }
                    `,
                    variables: {
                        refresh: localStorage.getItem("token_refresh"),
                    },
                })
                .then((result) => {
                    localStorage.setItem("token_access", result.data.refreshToken.access);
                })
                .catch((error) => {
                    this.$emit("logOut");
                    return;
                });
            await this.$apollo
                .mutate({
                mutation: gql`
                    mutation($ventas: VentasInput!) {
                        createVentas(ventas: $ventas) {
                            lastChange
                            id
                            nombre
                            cantidad
                            precio
                        }
                    }
                `,
                variables: {
                    ventas: this.createVentas,
                },
                })
                .then((result) => {
                    alert("Venta Realizada de Manera Correcta, Revise Historial");
                })
                .catch((error) => {
                    alert("Datos Incorrectos");
                });
        },
        sumar:function(){
            this.createVentas.preciototal = this.precio * this.createVentas.cantidad
            
            return;
        },
        
    }
      
}
</script>
<style>
    .container_venta{
        margin: 10%;
        display: flex;
        align-items: center;
        justify-content: center;
        
    }
    .list_products{

        border: 3px solid  #333;
        border-radius: 15px;
        width: 30%;
        height: 100%;
        
        background-image: linear-gradient(rgba(192, 192, 192, 0.74),rgba(58, 58, 58, 0.801));
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .B_realizarCompra{
        width: 100%;
        height: 40px;
        font-family: Verdana, Geneva, Tahoma, sans-serif  ;
        color: #E5E7E9;
        background: #1f1f1f;
        border: 1px solid #E5E7E9;
        font-weight: bold;
        border-radius: 10px;
        padding: 10px 25px;
        margin: 5px 0;
    }
    .B_realizarCompra:hover{
        color: #000000;
        background: rgba(5, 253, 58, 0.753);
        border: 1px solid #283747;
    }

    
</style>
