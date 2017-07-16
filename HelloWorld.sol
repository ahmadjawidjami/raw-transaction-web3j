pragma solidity ^0.4.0;
contract HelloWorld {
    string public message;

    mapping (address => string) users;

    mapping (string => string) globalIds;

    function HelloWorld(){
        message = "Ahmad Jawid";
        users[msg.sender] = "Jawid Jami";
    }

    function addUser(address userAddress, string name) returns (string){
        users[userAddress] = name;
    }


    function addGlobalId(string globalId, string socialRecordHash) returns (string){

        globalIds[globalId] = socialRecordHash;

    }

    function takeAsArray(string [] names){

    }

    function getSocailRecordHash(string globalId) constant returns (string socialRecordHash){

        socialRecordHash = globalIds[globalId];
        return socialRecordHash;
    }

    function updateSocialRecordHash(string globalId, string socialRecordHash) returns (string){
        globalIds[globalId] = socialRecordHash;
    }


    function setMessage(string theMessage) returns (string) {
        message = theMessage;
    }

    function getUser(address _user) constant returns (string _theUser){
        _theUser = users[_user];
        return _theUser;
    }
}
