'use strict';

angular.module('enigme30App')
    .factory('EnigmaCheck', function EnigmaCheck($http) {

        var EnigmaCheck = {

            check: function (answer) {
                return $http.post('/api/enigma/check', answer);
            }

        };

        return EnigmaCheck;
    });
