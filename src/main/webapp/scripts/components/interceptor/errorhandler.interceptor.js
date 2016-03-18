'use strict';

angular.module('enigme30App')
    .factory('errorHandlerInterceptor', function ($q, $rootScope) {
        return {
            'responseError': function (response) {
                if (!(response.status == 401 && response.data.path.indexOf("/api/account") == 0 )){
	                $rootScope.$emit('enigme30App.httpError', response);
	            }
                return $q.reject(response);
            }
        };
    });