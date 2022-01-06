<template>

    <div class="signUp_user">
        <div class="container_signUp_user">
            <div class="title_signup">
              <h2>Registrarse</h2>
            </div>

            <form v-on:submit.prevent="processSignUp" >
                <input type="text" v-model="user.username" placeholder="Usuario">
                <br>
                
                <input type="password" v-model="user.password" placeholder="Contraseña">
                <br>
                
                <input type="text" v-model="user.name" placeholder="Nombre">
                <br>

                <input type="email" v-model="user.email" placeholder="Correo">
                <br>

                <input type="number" v-model="user.balance" placeholder="Saldo Inicial">
                <br>

                <button type="submit">Registrarse</button>
            </form>
        </div>

    </div>

</template>


<script>
import gql from "graphql-tag";

export default {
    name: "SignUp",

    data: function() {
        return {
        user: {
            username: "",
            password: "",
            name: "",
            email: "",
            balance: 0,
        },
        };
    },

  methods: {
    processSignUp: async function() {
      await this.$apollo
        .mutate({
          mutation: gql`
            mutation($userInput: SignUpInput!) {
              signUpUser(userInput: $userInput) {
                refresh
                access
              }
            }
          `,
          variables: {
            userInput: this.user,
          },
        })
        .then((result) => {
          let dataLogIn = {
            username: this.user.username,
            token_access: result.data.signUpUser.access,
            token_refresh: result.data.signUpUser.refresh,
          };

          this.$emit("completedSignUp", dataLogIn);
        })
        .catch((error) => {
          alert("ERROR: Fallo en el registro.");
        });
    },

  },
}
</script>


<style>

    .signUp_user{
        margin: 0%;
        padding-top : 3%;
        padding-bottom: 5% ;
        height: 80%;
        width: 100%;
    
        display: flex;
        justify-content: center ;
        align-items: center;
    }
    .title_signup{
      margin-bottom: 5%;
      align-items: center;
      text-transform:uppercase;
    }

    .container_signUp_user {
        width: 30%;
        height: 100%;
        
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .signUp_user h2{
        color: #ffffff;
        margin: 0%;

    }

    .signUp_user form{
        width: 70%;
        
    }

    .signUp_user input{
        height: 40px;
        width: 100%;

        box-sizing: border-box;
        padding: 10px 20px;
        margin: 5px 0;
        background-color: transparent;
        color: white;
        
    }

    .signUp_user button{
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

    .signUp_user button:hover{
        color: #000000;
        background: rgb(10, 252, 30);
        border: 1px solid #283747;
    }

</style>