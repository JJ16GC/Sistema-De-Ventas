const { gql } = require('apollo-server');

const ventasTypeDefs = gql `
    type Ventas {
        id: String!
        nombre: String!
        cantidad: Int!
        precio: Int!
        lastChange: String!
    }

    input VentasInput {
        username: String!
        cantidad: Int!
        preciototal: Int!
    }

    extend type Query {
        ventasByUsername(username: String!): [Ventas]
    }

    extend type Mutation {
        createVentas(ventas: VentasInput!): Ventas
    }
`;
module.exports = ventasTypeDefs;