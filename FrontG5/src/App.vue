<template>
  <div id="app" class="app">
    <body>
      <div class="header">
        <div class="top_header">
          <div class="logo_header">
            <div class="button_header">
              <nav>
                <button v-if="is_auth" v-on:click="loadHome">Inicio</button>
                <button v-if="is_auth" v-on:click="loadAccount">Cuenta</button>
                <button v-if="is_auth" v-on:click="loadCarShop">Carrito</button>
                <button v-if="is_auth" v-on:click="loadTransaction">
                  Transacción
                </button>
                <button v-if="is_auth" v-on:click="logOut">
                  Cerrar Sesión
                </button>
                <button v-if="!is_auth" v-on:click="loadLogIn">
                  Iniciar Sesión
                </button>
                <button v-if="!is_auth" v-on:click="loadSignUp">
                  Registrarse
                </button>
              </nav>
            </div>
          </div>
        </div>
      </div>

      <div class="main-component">
        <router-view
          v-on:completedLogIn="completedLogIn"
          v-on:completedSignUp="completedSignUp"
          v-on:logOut="logOut"
        >
        </router-view>
      </div>
    </body>
    <div class="footer">
      <h2>Misión TIC 2022</h2>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",

  computed: {
    is_auth: {
      get: function() {
        return this.$route.meta.requiresAuth;
      },
      set: function() {},
    },
  },

  methods: {
    loadLogIn: function() {
      this.$router.push({ name: "logIn" });
    },

    loadSignUp: function() {
      this.$router.push({ name: "signUp" });
    },

    completedLogIn: function(data) {
      localStorage.setItem("username", data.username);
      localStorage.setItem("token_access", data.token_access);
      localStorage.setItem("token_refresh", data.token_refresh);
      alert("Autenticación Exitosa");
      this.loadHome();
    },

    completedSignUp: function(data) {
      alert("Registro Exitoso");
      this.completedLogIn(data);
    },

    loadHome: function() {
      this.$router.push({ name: "home" });
    },

    loadAccount: function() {
      this.$router.push({ name: "account" });
    },

    loadTransaction: function() {
      this.$router.push({ name: "transaction" });
    },
    loadCarShop: function() {
      this.$router.push({ name: "carShop" });
    },

    logOut: function() {
      localStorage.clear();
      alert("Sesión Cerrada");
      this.loadLogIn();
    },
  },
};
</script>

<style>


body {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  width: 100%;
  height: 100%;
  max-height: 100%;
  margin: 0;
  contain: size;
}
input {
  border-radius: 30px;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  height: 40px;
  width: 100%;
  box-sizing: border-box;
  padding: 10px 20px;
  margin: 5px 0;
  border: 1px solid #f3f5f7;
}
.header {
  border: none;
  margin: 0%;
  padding: 1px 0;
  width: 100%;
  height: 10%;
  min-height: 60px;
  max-height: 400px;
  background-color: rgb(32, 29, 46);
  color: #e5e7e9;
  display: inline-table;
}



.logo_header {
  background-size: unset;
  background-position: center center;
  background-repeat: no-repeat;
  overflow: hidden;
  width: 100%;
  height: 100%;
  display: flex;
  /*background-image: url('~@/assets/tecnoteam1.png');*/
  flex-direction: column-reverse;
  cursor: pointer;
}

.button_header {
  display: flex;
  flex-direction: row-reverse;
}
.header nav {
  align-items: center;
}

.header nav button {
  font-size: 17px;
  color: #e5e7e9;
  background: rgb(32, 29, 46);
  border: none;
  cursor: pointer;
  padding:10px 20px;
  float: left;
  margin-top: 10px;
  margin-right: 15px;
}

.header nav button:hover {
  color: #ffffff;
  background: rgb(63, 63, 63);
  border: none;
}

.main-component {
  height: 85vh;
  margin: 0%;
  padding: 0%;
  display: flex;
  background-image: url('~@/assets/fondo.jpg');
  
}

.footer {
  position:absolute;
  bottom: 0;
  margin: 0%;
  padding: 0;
  width: 100%;
  height: 8%;
  margin-top: 10px;
  background-color: rgb(32, 29, 46);
  color: #e5e7e9;
}

h2 {
  font-size: 17px;
}

.footer h2 {

  display: flex;
  justify-content: center;
}
</style>
