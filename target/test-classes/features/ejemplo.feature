@mobile @chrome
Feature: Prueba de ejemplo en navegador móvil
  Como usuario
  Quiero abrir Chrome en mi dispositivo móvil
  Para verificar que el framework funciona correctamente

  @smoke
  Scenario: Abrir Chrome y verificar que carga
    Given el usuario abre la aplicación Chrome
    When el usuario espera 3 segundos
    Then la aplicación Chrome debería estar visible