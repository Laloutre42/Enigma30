'use strict';

angular.module('enigme30App')
    .factory('EnigmaExecution', function EnigmaExecution() {
        return $resource('api/enigmaExecution');
    });
