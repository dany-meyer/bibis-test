// Lasttest für die BibIS-Oberfläche mit k6 (https://k6.io)
// Vorbereitung im Ordner bibis-test:
//   python3 -m http.server 8080 --directory bibis-web
// Ausführen in einem zweiten Terminal:
//   k6 run src/test/k6/lasttest.js
import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 50,           // gleichzeitige Nutzende
  duration: '30s',   // Dauer
  thresholds: {      // Qualitätsziel
    http_req_duration: ['p(95)<2000'],
  },
};

export default function () {
  const res = http.get('http://localhost:8080/index.html');
  check(res, { 'Status 200': (r) => r.status === 200 });
  sleep(1);          // 1 s Pause
}
