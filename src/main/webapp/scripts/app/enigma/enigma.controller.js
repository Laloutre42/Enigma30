'use strict';

angular.module('enigme30App')
    .controller('EnigmaController', function ($scope, $log, $state, currentEnigmaExecution, EnigmaExecution) {

        var vm = this;

        const NOT_FOUND = "NOT_FOUND";
        const FOUND = "FOUND";
        const FINISHED = "FINISHED";

        const BEGINNING = "BEGINNING";
        const BETWEEN_TWO_ENIGMAS_FOUND = "BETWEEN_TWO_ENIGMAS_FOUND";
        const BETWEEN_TWO_ENIGMAS_NOT_FOUND = "BETWEEN_TWO_ENIGMAS_NOT_FOUND";
        const END = "END";

        var startTime = new Date().getTime();
        $log.debug("[EnigmaController] currentEnigmaExecution: ", currentEnigmaExecution);

        if (angular.isDefined(currentEnigmaExecution)) {
            vm.number = currentEnigmaExecution.number;
            vm.question = currentEnigmaExecution.question;
            vm.indice = currentEnigmaExecution.indice;
        }

        vm.isCollapsed = false;

        vm.submit = function () {

            var answerEnigma = {};
            answerEnigma.number = vm.number;
            answerEnigma.question = vm.question;
            answerEnigma.answer = vm.answer;

            var executionTime = new Date().getTime() - startTime;
            $log.debug("[EnigmaController] submit answerEnigma: ", answerEnigma, " in ", executionTime, " ms");

            EnigmaExecution.saveEnigmaExecution(answerEnigma, executionTime).then(
                function (response) {
                    $log.debug(response.data);

                    if (angular.isDefined(response.data) && angular.isDefined(response.data.enigmaExecutionResult) && response.data.enigmaExecutionResult === NOT_FOUND) {
                        $log.info("[EnigmaController] NOT FOUND");
                        $state.go('enigmaTransition', {type: BETWEEN_TWO_ENIGMAS_NOT_FOUND, executionResult: response.data.answerResult});
                    }

                    if (angular.isDefined(response.data) && angular.isDefined(response.data.enigmaExecutionResult) && response.data.enigmaExecutionResult === FOUND) {
                        $log.info("[EnigmaController] FOUND");
                        $state.go('enigmaTransition', {type: BETWEEN_TWO_ENIGMAS_FOUND, executionResult: response.data.answerResult});
                    }

                    if (angular.isDefined(response.data) && angular.isDefined(response.data.enigmaExecutionResult) && response.data.enigmaExecutionResult === FINISHED) {
                        $log.info("[EnigmaController] FINISHED");
                        $state.go('enigmaTransition', {type: END, executionResult: response.data.answerResult});
                    }

                },
                function (error) {
                    $log.error(error);
                });
        };

        // Leaflet map
        angular.extend($scope, {
            center: {
                lat: 45.38,
                lng: 4.5,
                zoom: 11
            },
            layers: {
                baselayers: {
                    osm: {
                        name: 'OpenStreetMap',
                        url: 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                        type: 'xyz'
                    }
                }
            },
            markers: {
                m1: {
                    lat: 45.42082,
                    lng: 4.41095,
                    message: "Le Rond-Point",
                    focus: false,
                    draggable: false,
                    opacity: (vm.number > 1) ? 1 : 0
                },
                m2: {
                    lat: 45.410247,
                    lng: 4.446199,
                    message: "Château de Rochetaillée",
                    focus: false,
                    draggable: false,
                    opacity: (vm.number > 2) ? 1 : 0
                },
                m3: {
                    lat: 45.37337,
                    lng: 4.49098,
                    message: "Cimetière de Tarentaise",
                    focus: false,
                    draggable: false,
                    opacity: (vm.number > 3) ? 1 : 0
                },
                m4: {
                    lat: 45.32709,
                    lng: 4.55477,
                    message: "Thélis-la-Combe",
                    focus: false,
                    draggable: false,
                    opacity: (vm.number > 4) ? 1 : 0
                },
                m5: {
                    lat: 45.26680,
                    lng: 4.54320,
                    message: "Montchal",
                    focus: false,
                    draggable: false,
                    opacity: (vm.number > 5) ? 1 : 0
                },
                m6: {
                    lat: 45.24680,
                    lng: 4.52272,
                    message: "Maison dans la Nature",
                    focus: false,
                    draggable: false,
                    opacity: (vm.number > 6) ? 1 : 0
                }
            },
            defaults: {
                scrollWheelZoom: false
            }
        });

    });
