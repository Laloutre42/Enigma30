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
                        controller: 'MainController as vm'
                    }
                },
                resolve: {}
            })
            .state('enigmaStatistics', {
                parent: 'site',
                url: '/enigmaStatistics',
                params: {
                    'type': null
                },
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/enigma-statistics/enigma-statistics.html',
                        controller: 'EnigmaStatisticsController as vm'
                    }
                },
                resolve: {
                    tryNumberForEachUser: function (EnigmaExecution) {
                        return EnigmaExecution.getTryNumberForEachUser()
                            .then(function (response) {
                                return response.data;
                            });
                    }
                }
            })
            .state('enigma', {
                parent: 'site',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/enigma/enigma.html',
                        controller: 'EnigmaController as vm'
                    }
                },
                resolve: {
                    currentEnigmaExecution: function (EnigmaExecution) {
                        return EnigmaExecution.getCurrentEnigmaForUser()
                            .then(function (response) {
                                return response.data;
                            });
                    }
                }
            })
            .state('enigmaTransition', {
                parent: 'site',
                url: '/enigma',
                params: {
                    'type': null,
                    'executionResult': null,
                },
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/enigma-transition/enigma-transition.html',
                        controller: 'EnigmaTransitionController as vm'
                    }
                },
                resolve: {
                    currentEnigmaStateExecution: function (EnigmaExecution) {
                        return EnigmaExecution.getCurrentEnigmaStateForUser()
                            .then(function (response) {
                                return response.data;
                            });
                    }
                }
            });
    });
