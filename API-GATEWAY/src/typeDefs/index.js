//Se llama al typedef (esquema) de cada submodulo
const accountTypeDefs = require('./account_type_defs');
const transactionTypeDefs = require('./transaction_type_defs');
const authTypeDefs = require('./auth_type_defs');
const ventasTypeDefs = require ('./ventas_type_defs');
//Se unen
const schemasArrays = [authTypeDefs, accountTypeDefs, transactionTypeDefs,
                         ventasTypeDefs];
//Se exportan
module.exports = schemasArrays;