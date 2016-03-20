'use strict';

angular.module('enigme30App')
    .controller('EnigmaController', function ($log, $state, currentEnigmaExecution, EnigmaExecution) {

        var vm = this;

        const NOT_FOUND = "NOT_FOUND";
        const FOUND = "FOUND";
        const FINISHED = "FINISHED";

        const BEGINNING = "BEGINNING";
        const BETWEEN_TWO_ENIGMAS_FOUND = "BETWEEN_TWO_ENIGMAS_FOUND";
        const BETWEEN_TWO_ENIGMAS_NOT_FOUND = "BETWEEN_TWO_ENIGMAS_NOT_FOUND";
        const END = "END";

        $log.debug("[EnigmaController] currentEnigmaExecution: ", currentEnigmaExecution);

        if (angular.isDefined(currentEnigmaExecution)) {
            vm.number = currentEnigmaExecution.number;
            vm.question = currentEnigmaExecution.question;
        }

        vm.submit = function () {

            var answerEnigma = {};
            answerEnigma.number = vm.number;
            answerEnigma.question = vm.question;
            answerEnigma.answer = vm.answer;

            $log.debug("[EnigmaController] submit answerEnigma: ", answerEnigma);

            EnigmaExecution.saveEnigmaExecution(answerEnigma).then(
                function (response) {
                    $log.debug(response.data);

                    if (angular.isDefined(response.data) && response.data === NOT_FOUND) {
                        $log.info("[EnigmaController] NOT FOUND");
                        $state.go('enigmaTransition', {type: BETWEEN_TWO_ENIGMAS_NOT_FOUND});
                    }

                    if (angular.isDefined(response.data) && response.data === FOUND) {
                        $log.info("[EnigmaController] FOUND");
                        $state.go('enigmaTransition', {type: BETWEEN_TWO_ENIGMAS_FOUND});
                    }

                    if (angular.isDefined(response.data) && response.data === FINISHED) {
                        $log.info("[EnigmaController] FINISHED");
                        $state.go('enigmaTransition', {type: END});
                    }

                },
                function (error) {
                    $log.error(error);
                });
        };

    });
