'use strict';

angular.module('enigme30App')
    .factory('Enigma', function Enigma($resource) {
        return $resource('api/enigma');
    });
