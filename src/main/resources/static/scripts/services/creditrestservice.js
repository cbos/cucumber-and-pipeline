'use strict';

/**
 * @ngdoc service
 * @name dockerUiApp.creditRestService
 * @description
 * # creditRestService
 * Service in the dockerUiApp.
 */
var creditRestService = angular.module('dockerUiApp')
  .service('creditRestService', function () {
    // AngularJS will instantiate a singleton by calling "new" on this function

  });


creditRestService.factory('CreditRequestsRestService', ['$resource',
  function ($resource) {
    return $resource('/creditRequests/:id', {}, {
      query: {method: 'GET', params: {id: ''}, isArray: true}
    });
  }]);

creditRestService.factory('CreateCreditRequest', ['$http',
  function ($http) {
    return {
      sendCreditRequest: function (creditRequest) {
        return $http.post('/creditRequest', creditRequest);
      }
    };
  }]);


