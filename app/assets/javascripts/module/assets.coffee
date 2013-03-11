angular.module('assets', ['ngResource'])
  .factory 'cache', () ->
    _cache = {}
    cache =
      has: (key) ->
        key of _cache
      get: (key) ->
        if key of _cache
          _cache[key] 
        else
          false
      put: (key,item) ->
        _cache[key] = item
        item
  
  .factory 'RepoGenerator', ($resource, $http, cache, $q) ->
    (ResourceInfo) =>
      class Repo
        inject: (data, url) ->
          if data instanceof Array
            cache.put url, (@inject(item) for item in data)
          else
            cache.put ResourceInfo.url({id: data.id}), ResourceInfo.createResource(data)
        
        get: (params, many) =>
          deferred = $q.defer()
          resourceUrl = ResourceInfo.url(params)
          
          if not many and cache.has resourceUrl
            deferred.resolve cache.get resourceUrl
          else if many and cache.has resourceUrl
          else
            $http
                method: 'GET'
                url: "/json/#{resourceUrl}"
                isArray: many
              .success (data) =>
                deferred.resolve @inject(data, resourceUrl)
              .error (reason) ->
                console.error "GET Request of URL /json/#{resourceUrl} with reason #{reason}"
          deferred.promise
      new Repo()
  
  .factory 'ProjectRepo', (cache, RepoGenerator) ->
    class ProjectInfo
      contructor: () ->
        @name = "projects"
      url: (p) ->
        if p.id?
          return "projects/#{p.id}"
        else
          return "projects"
      createResource: (data) ->
        data: data
        sprints: []
    RepoGenerator new ProjectInfo()
    
  .factory 'SprintRepo', ($resource, cache, RepoGenerator) ->
    class SprintInfo
      contructor: () ->
        @name = "sprints"
      url: (p) ->
        if 'id' of p
          return "sprints/#{p.id}"
        else if 'project_id' of p
          return "projects/#{p.project_id}/sprints"
        else
          return "sprints/"
      createResource: (data) ->
        data: data
        stories: []
    RepoGenerator new SprintInfo()
      
  .factory 'StoryRepo', ($resource, cache, RepoGenerator) ->
    class StoryInfo
      contructor: () ->
        @name = "stories"
      url: (p) ->
        if 'id' of p
          return "stories/#{p.id}"
        else if 'sprint_id' of p
          return "sprints/#{p.sprint_id}/stories"
        else
          return "stories/"
      createResource: (data) ->
        data: data
        tasks: []
    RepoGenerator new StoryInfo()
    
    
    
    
    