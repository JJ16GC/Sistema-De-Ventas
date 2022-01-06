const ventasResolver = {
    Query: {
        ventasByUsername: async(_, { username }, { dataSources, userIdToken }) => {
            usernameToken = (await dataSources.authAPI.getUser(userIdToken)).username
            if (username == usernameToken)
                return dataSources.accountAPI.ventasByUsername(username)
            else
                return null
    
        }
    },
    Mutation: {
        createVentas: async(_, { ventas }, { dataSources, userIdToken }) => {
            usernameToken = (await dataSources.authAPI.getUser(userIdToken)).username
            if (ventas.username == usernameToken)
                return dataSources.accountAPI.createVentas(ventas)
            else
                return null
    
        }
    }
};
module.exports = ventasResolver;