export const environment = {
    production: false,
    stompConfig: {
      url: 'ws://localhost:8080/ws', // Your backend WebSocket URL
      headers: {},
      heartbeat_in: 0,
      heartbeat_out: 20000,
      reconnect_delay: 5000
    }
  };
  