'use strict';

angular.module('enigme30App')
    .controller('MainController', function ($state, Principal) {

        var vm = this;
        vm.start = start;

        Principal.identity().then(function (account) {
            vm.account = account;
            vm.isAuthenticated = Principal.isAuthenticated;
        });

        function start() {
            $state.go("enigmaTransition");
        }
    });
