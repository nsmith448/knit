angular.module('main', ['assets', 'ngSanitize'])
  .config ($routeProvider, $locationProvider) ->
    $locationProvider.html5Mode on
    $routeProvider
      .when '/',
        controller: MainCntl
        templateUrl: '/templates/project.html'
        
      .when '/projects/:id',
        controller: ProjectCntl
        templateUrl: '/templates/project.html'

onlyActiveSprints = (sprint) ->
  new Date(sprint.data.begin_date) < new Date() < new Date(sprint.data.end_date)

onlyBacklogSprints = (sprint) ->
  new Date(sprint.data.end_date) < new Date()

onlyFutureSprints = (sprint) ->
  new Date(sprint.data.begin_date) > new Date()

@MainCntl = ($scope, $location, ProjectRepo) ->
  $scope.currentProject = { data: { title: 'Loading...' } }
  $scope.projects = ProjectRepo.get {}
  $scope.projUrl = (id) -> '/projects/' + id
  $scope.setActiveSprints = () -> $scope.sprintFilter = onlyActiveSprints; $location.search "filter","active"
  $scope.setBacklogSprints = () -> $scope.sprintFilter = onlyBacklogSprints; $location.search "filter","backlog"
  $scope.setFutureSprints = () -> $scope.sprintFilter = onlyFutureSprints; $location.search "filter", "future"
  
  $scope.selectedSprints = (str) ->  if ($location.search().filter || 'active') == str then 'active' else ''
  
  switch $location.search().filter
    when "active" then $scope.sprintFilter = onlyActiveSprints
    when "backlog" then $scope.sprintFilter = onlyBacklogSprints
    when "future" then $scope.sprintFilter = onlyFutureSprints
    else $scope.sprintFilter = onlyActiveSprints

@ProjectCntl = ($scope, $routeParams, ProjectRepo, SprintRepo) ->
  if __PRELOAD__
    ProjectRepo.inject __PRELOAD__
  ProjectRepo.get({id: $routeParams.id})
    .then (proj) ->
      $scope.currentProject.data.title = proj.data.title
      $scope.project = proj
      proj.sprints = SprintRepo.get { project_id: proj.data.id }
 
@SprintCntl = ($scope, SprintRepo, StoryRepo) ->
  $scope.loadStories = () ->
    $scope.sprint.stories = StoryRepo.get { sprint_id: $scope.sprint.data.id }