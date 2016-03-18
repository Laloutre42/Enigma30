'use strict';

angular.module('enigme30App')
    .controller('EnigmaController', function ($scope, Principal, enigma, EnigmaCheck) {

        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        if (typeof enigma !== 'undefined' && typeof enigma[0] !== 'undefined') {
            $scope.number = enigma[0].number;
            $scope.question = enigma[0].question;
        }

        $scope.submit = function () {

            var answerEnigma = {};
            answerEnigma.number = $scope.number;
            answerEnigma.question = $scope.question;
            answerEnigma.answer = $scope.answer;

            EnigmaCheck.check(answerEnigma).$promise.then(
                function (data) {
                    console.log(data);

                },
                function (error) {
                    console.error(error);
                });
        };

    });
