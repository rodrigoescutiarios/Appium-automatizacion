@mobile @chrome @search
Feature: Búsqueda en Chrome
  Como usuario
  Quiero buscar información en Chrome
  Para encontrar contenido relevante

  @smoke
  Scenario: Buscar la palabra selenium en Chrome
    Given el usuario abre Chrome en el dispositivo
    When el usuario busca "selenium" en Chrome
    Then el usuario debería ver resultados de búsqueda