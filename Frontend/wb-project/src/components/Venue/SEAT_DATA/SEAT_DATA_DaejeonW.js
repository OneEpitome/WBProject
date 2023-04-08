import W from '../../../images/venue/대전월드컵경기장/W석.png';
import W_Core from '../../../images/venue/대전월드컵경기장/W석 코어.png';
import E from '../../../images/venue/대전월드컵경기장/E석.png';
import E_Core from '../../../images/venue/대전월드컵경기장/E석 코어.png';
import S from '../../../images/venue/대전월드컵경기장/S석.png';
import S_Core from '../../../images/venue/대전월드컵경기장/S석 코어.png';
import Table from '../../../images/venue/대전월드컵경기장/TABLE석.png'
import Pub from '../../../images/venue/대전월드컵경기장/하나라운지펍.png'
import Deck from '../../../images/venue/대전월드컵경기장/데크존(4인).png'
import N from '../../../images/venue/대전월드컵경기장/N석(원정석).png'

export const SEAT_DATA_DaejeonW = [
  {
    id: 'W석',
    sector: 'W석',
    src: W,
    details: ['W21', 'W22', 'W25', 'W26'],
  },
  {
    id: 'W석 코어',
    sector: 'W석 코어',
    src: W_Core,
    details: ['W23', 'W24'],
  },
  {
    id: 'S석',
    sector: 'S석',
    src: S,
    details: ['S21', 'S22', 'S23', 'S24', 'S25'],
  },
  {
    id: 'S석 코어',
    sector: 'S석 코어',
    src: S_Core,
    details: ['S23'],
  },
  {
    id: 'E석',
    sector: 'E석',
    src: E,
    details: ['E21', 'E22', 'E23', 'E24', 'E25', 'E26', 'E27'],
  },
  {
    id: 'E석 코어',
    sector: 'E석 코어',
    src: E_Core,
    details: ['E23', 'E24', 'E25'],
  },
  {
    id: 'TABLE석',
    sector: 'TABLE석',
    src: Table,
    details: ['T-W22', 'T-W24'],
  },
  {
    id: '하나라운지펍',
    sector: '하나라운지펍',
    src: Pub,
    details: ['E27'],
  },
  {
    id: '데크존(4인)',
    sector: '데크존(4인)',
    src: Deck,
    details: ['E23', 'E25'],
  },
  {
    id: 'N석(원정석)',
    sector: 'N석(원정석)',
    src: N,
    details: ['N21', 'N22', 'N23', 'N24', 'N25'],
  },
];