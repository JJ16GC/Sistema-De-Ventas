<template>

    <div class="logIn_user">
        <div class="container_logIn_user">

            <form v-on:submit.prevent="processLogInUser" >
                <input type="text" v-model="user.username" placeholder="Usuario">
                <br>
                <input type="password" v-model="user.password" placeholder="Contraseña">
                <br>
                <button  type="submit">Iniciar Sesión</button>
            </form>
        </div>

    </div>

</template>


<script>
import gql from "graphql-tag";

export default {
  name: "LogIn",

  data: function() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },

  methods: {
    processLogInUser: async function() {
      await this.$apollo
        .mutate({
          mutation: gql`
            mutation($credentials: CredentialsInput!) {
              logIn(credentials: $credentials) {
                refresh
                access
              }
            }
          `,
          variables: {
            credentials: this.user,
          },
        })
        .then((result) => {
          let dataLogIn = {
            username: this.user.username,
            token_access: result.data.logIn.access,
            token_refresh: result.data.logIn.refresh,
          };

          this.$emit("completedLogIn", dataLogIn);
        })
        .catch((error) => {
          alert("ERROR 401: Credenciales Incorrectas.");
        });
    },
  },
}
</script>


<style>

    .logIn_user{
        margin: 0;
        padding: 0%;
        height: 100%;
        width: 100%;
    
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .container_logIn_user {
        border-radius: 15px;
        width: 25%;
        height: 60%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .logIn_user h2{
        color: #333;

    }

    .logIn_user form{
        width: 70%;
        
    }

    .logIn_user input{
        height: 40px;
        width: 100%;

        box-sizing: border-box;
        padding: 0px 20px;
        margin: 10px 0;
        
        border: 1px solid #f3f5f7;
        background-color: transparent;
        color: white;
    }

    .logIn_user button{
        width: 100%;
        height: 40px;
        font-family: Verdana, Geneva, Tahoma, sans-serif  ;
        color: #E5E7E9;
        background: #1f1f1f;
        border: 1px solid #E5E7E9;
        font-weight: bold;
        border-radius: 10px;
        padding: 10px 25px;
        margin: 25px 0;
    }

    .logIn_user button:hover{
        color: #000000;
        background: rgb(99, 89, 136);
        border: 1px solid #283747;
    }

</style>