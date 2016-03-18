'use strict';

angular.module('enigme30App')
    .controller('EnigmaController', function ($log, $scope, Principal, currentEnigmaExecution, EnigmaExecution) {

        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        if (angular.isDefined(currentEnigmaExecution)) {
            $scope.number = currentEnigmaExecution.number;
            $scope.question = currentEnigmaExecution.question;
        }

        $scope.submit = function () {

            var answerEnigma = {};
            answerEnigma.number = $scope.number;
            answerEnigma.question = $scope.question;
            answerEnigma.answer = $scope.answer;

            EnigmaExecution.save(answerEnigma).$promise.then(
                function (data) {
                    $log.debug(data);

                },
                function (error) {
                    $log.error(error);
                });
        };

    });
