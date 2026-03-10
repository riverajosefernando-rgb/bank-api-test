Feature: Bank API Mock

  Background:

    * def accounts =

  """
  {
 "1001": { "balance": 1000 },
 "2001": { "balance": 500 }
}
  """
    * def transactions = []

  Scenario: pathMatches('/auth/login') && methodIs('post')

    * def uuidUtils = call read('classpath:utils/dataUtils.js')

    * def token = uuidUtils.uuid()

    * def response =
"""
{
  "token": "#(token)"
}
"""
    * def responseStatus = 200

  Scenario: pathMatches('/accounts/{id}') && methodIs('get')

    * def id = pathParams.id
    * def account = accounts[id]

    * if (!account) karate.abort()

    * def response =
"""
{
 "accountId": "#(id)",
 "balance": #(account.balance)
}
"""
    * def responseStatus = 200

  Scenario: pathMatches('/transfers') && methodIs('post')

    * def uuidUtils = call read('classpath:utils/dataUtils.js')



    * def from = request.fromAccount
    * def to = request.toAccount
    * def amount = request.amount

    * def fromAcc = accounts[from]
    * def toAcc = accounts[to]

    * def result =
"""
function(){

  if (!fromAcc || !toAcc) {
    return { status: 404, body: { error: 'ACCOUNT_NOT_FOUND' } };
  }

  if (fromAcc.balance < amount) {
    return { status: 400, body: { error: 'INSUFFICIENT_FUNDS' } };
  }

  var txId = uuidUtils.randomNumber(10) + '';

  fromAcc.balance = fromAcc.balance - amount;
  toAcc.balance = toAcc.balance + amount;

  transactions.push({
    transactionId: txId,
    from: from,
    to: to,
    amount: amount
  });

  return {
    status: 201,
    body: {
      status: 'SUCCESS',
      transactionId: txId,
      amount: amount
    }
  };

}()
"""

    * def response = result.body
    * def responseStatus = result.status


  Scenario: pathMatches('/transactions') && methodIs('get')

    * def response = transactions
    * def responseStatus = 200