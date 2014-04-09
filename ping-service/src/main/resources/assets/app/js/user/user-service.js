'use strict';

angular.module('ping')
  .factory('User', ['$resource', function ($resource) {
    return $resource('ping/users/:id', {}, {
      'query': { method: 'GET', isArray: true},
      'get': { method: 'GET'},
      'update': { method: 'PUT'}
    });
  }]);
