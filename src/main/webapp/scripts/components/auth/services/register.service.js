'use strict';

angular.module('enigme30App')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


