'use strict';

angular.module('enigme30App')
    .controller('EnigmaStatisticsController', function ($scope, $log, $state, tryNumberForEachUser) {

        var vm = this;
        vm.objectToArray = objectToArray;

        $log.debug("[EnigmaController] tryNumberForEachUser: ", tryNumberForEachUser);

        if (angular.isDefined(tryNumberForEachUser)) {
            vm.data = vm.objectToArray(tryNumberForEachUser);
        }

        function objectToArray(input) {
            var out = [];
            for (var i in input) {
                out.push(input[i]);
            }
            return out;
        }

    });
