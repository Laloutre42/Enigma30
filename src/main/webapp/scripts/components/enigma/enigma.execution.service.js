'use strict';

angular.module('enigme30App')
    .factory('EnigmaExecution', function EnigmaExecution($http) {
        return {

            // Get current Enigma for user
            getCurrentEnigmaForUser: function () {
                return $http.get('/api/enigmaExecution');
            },

            // Get current Enigma STATE for user
            getCurrentEnigmaStateForUser: function () {
                return $http.get('/api/enigmaExecution/state');
            },

            // Save Enigma execution for user
            saveEnigmaExecution: function (answer) {
                return $http.post('/api/enigmaExecution', answer);
            }
        }
    });
