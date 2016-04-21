'use strict';

angular.module('enigme30App')
    .controller('EnigmaTransitionController', function ($scope, $log, $stateParams, $state, currentEnigmaStateExecution) {

        const BEGINNING = "BEGINNING";
        const BETWEEN_TWO_ENIGMAS_FOUND = "BETWEEN_TWO_ENIGMAS_FOUND";
        const BETWEEN_TWO_ENIGMAS_NOT_FOUND = "BETWEEN_TWO_ENIGMAS_NOT_FOUND";
        const END = "END";


        function isBeginning() {
            return vm.type === BEGINNING;
        }

        function isBetweenTwoEnigmasFound() {
            return vm.type === BETWEEN_TWO_ENIGMAS_FOUND;
        }

        function isBetweenTwoEnigmasNotFound() {
            return vm.type === BETWEEN_TWO_ENIGMAS_NOT_FOUND;
        }

        function isEnd() {
            return vm.type === END;
        }

        function nextEnigma() {
            $state.go('enigma');
        }

        var vm = this;

        vm.type = angular.copy($stateParams.type) || currentEnigmaStateExecution || BEGINNING;
        vm.isBeginning = isBeginning;
        vm.isBetweenTwoEnigmasFound = isBetweenTwoEnigmasFound;
        vm.isBetweenTwoEnigmasNotFound = isBetweenTwoEnigmasNotFound;
        vm.isEnd = isEnd;
        vm.nextEnigma = nextEnigma;

        $log.debug("[EnigmaTransitionController] vm.type is", vm.type);

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
                m7: {
                    lat: 45.24680,
                    lng: 4.52272,
                    message: "Maison dans la Nature",
                    focus: true,
                    draggable: false
                }
            },
            defaults: {
                scrollWheelZoom: false
            }
        });
    });
