'use strict';

angular.module('enigme30App')
    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/main.html',
                        controller: 'MainController'
                    }
                },
                resolve: {}
            })
            .state('enigma', {
                parent: 'site',
                url: '/enigma',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/enigma/enigma.html',
                        controller: 'EnigmaController'
                    }
                },
                resolve: {
                    currentEnigmaExecution: function (EnigmaExecution) {
                        return EnigmaExecution.query()
                    }
                }
            });
    });
