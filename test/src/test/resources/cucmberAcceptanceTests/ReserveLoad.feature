Feature: ReserveLoad

Scenario: Reserve a load
    Given a load with content coal
    When you reserve it
    Then the load will be reserved for you