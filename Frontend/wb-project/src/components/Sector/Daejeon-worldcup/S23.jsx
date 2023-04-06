import React from 'react';
import Seat from '../Seat';
import Sector from '../Sector';
import SectorImg from '../../../images/sector/대전월드컵경기장/S23.png';
import styled from 'styled-components';

const Data = styled.div`
  width:100px;
  display: none;
`;

export default function S23() {
  const seatData = [
    { '2열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '4열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '6열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '8열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '10열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '12열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '13열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '15열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '16열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '17열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '18열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '19열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '20열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '21열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
    { '22열': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,] },
  ];

  return (
    <Sector src={SectorImg}>
      {/* 2열 오른쪽부터 1번 좌석 */}
      {
        seatData[0]['2열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`2열 ${v}`} top='179px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 2열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[0]['2열'].slice(16, 32).map((v, i) => {
          return <Seat
            key={`S23 2열 ${v}`} top='179px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 2열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[0]['2열'].slice(32, 48).map((v, i) => {
          return <Seat
            key={`S23 2열 ${v}`} top='179px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 2열 ${v}`}</Data></Seat>
        })
      }

      {/* 4열 */}
      {
        seatData[1]['4열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`4열 ${v}`} top='219px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 4열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[1]['4열'].slice(16, 32).map((v, i) => {
          return <Seat
            key={`S23 4열 ${v}`} top='219px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 4열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[1]['4열'].slice(32, 48).map((v, i) => {
          return <Seat
            key={`S23 4열 ${v}`} top='219px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 4열 ${v}`}</Data></Seat>
        })
      }

      {/* 6열 */}
      {
        seatData[2]['6열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`4열 ${v}`} top='259px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 6열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[2]['6열'].slice(16, 32).map((v, i) => {
          return <Seat
            key={`S23 6열 ${v}`} top='259px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 6열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[2]['6열'].slice(32, 48).map((v, i) => {
          return <Seat
            key={`S23 6열 ${v}`} top='259px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 6열 ${v}`}</Data></Seat>
        })
      }

      {/* 8열 */}
      {
        seatData[3]['8열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`8열 ${v}`} top='299px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 8열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[3]['8열'].slice(16, 32).map((v, i) => {
          return <Seat
            key={`S23 8열 ${v}`} top='299px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 8열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[3]['8열'].slice(32, 48).map((v, i) => {
          return <Seat
            key={`S23 8열 ${v}`} top='299px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 8열 ${v}`}</Data></Seat>
        })
      }
      {/* 10열 */}
      {
        seatData[4]['10열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`10열 ${v}`} top='339px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 10열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[4]['10열'].slice(16, 32).map((v, i) => {
          return <Seat
            key={`S23 10열 ${v}`} top='339px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 10열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[4]['10열'].slice(32, 48).map((v, i) => {
          return <Seat
            key={`S23 10열 ${v}`} top='339px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 10열 ${v}`}</Data></Seat>
        })
      }
      {/* 12열 */}
      {
        seatData[5]['12열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`12열 ${v}`} top='379px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 12열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[5]['12열'].slice(16, 32).map((v, i) => {
          return <Seat
            key={`S23 12열 ${v}`} top='379px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 12열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[5]['12열'].slice(32, 48).map((v, i) => {
          return <Seat
            key={`S23 4열 ${v}`} top='379px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 12열 ${v}`}</Data></Seat>
        })
      }

      {/* 13열 */}
      {
        seatData[6]['13열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`13열 ${v}`} top='399px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 13열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[6]['13열'].slice(16, 32).map((v, i) => {
          return <Seat
            key={`S23 13열 ${v}`} top='399px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 13열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[6]['13열'].slice(32, 48).map((v, i) => {
          return <Seat
            key={`S23 13열 ${v}`} top='399px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 13열 ${v}`}</Data></Seat>
        })
      }

      {/* 15열 */}
      {
        seatData[7]['15열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`15열 ${v}`} top='479px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 15열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[7]['15열'].slice(16, 20).map((v, i) => {
          return <Seat
            key={`S23 15열 ${v}`} top='479px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 15열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[7]['15열'].slice(20, 24).map((v, i) => {
          return <Seat
            key={`S23 15열 ${v}`} top='479px' left={`${480 - (i * 15)}px`} >
            <Data>{`S23 15열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[7]['15열'].slice(24, seatData[7]['15열'].length).map((v, i) => {
          return <Seat
            key={`S23 15열 ${v}`} top='479px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 15열 ${v}`}</Data></Seat>
        })
      }

      {/* 16열 */}
      {
        seatData[8]['16열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`16열 ${v}`} top='499px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 16열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[8]['16열'].slice(16, 20).map((v, i) => {
          return <Seat
            key={`S23 16열 ${v}`} top='499px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 16열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[8]['16열'].slice(20, 24).map((v, i) => {
          return <Seat
            key={`S23 16열 ${v}`} top='499px' left={`${480 - (i * 15)}px`} >
            <Data>{`S23 16열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[8]['16열'].slice(24, seatData[8]['16열'].length).map((v, i) => {
          return <Seat
            key={`S23 16열 ${v}`} top='499px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 16열 ${v}`}</Data></Seat>
        })
      }

      {/* 17열 */}
      {
        seatData[9]['17열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`17열 ${v}`} top='519px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 17열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[9]['17열'].slice(16, 20).map((v, i) => {
          return <Seat
            key={`S23 17열 ${v}`} top='519px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 17열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[9]['17열'].slice(20, 24).map((v, i) => {
          return <Seat
            key={`S23 17열 ${v}`} top='519px' left={`${480 - (i * 15)}px`} >
            <Data>{`S23 17열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[9]['17열'].slice(24, seatData[9]['17열'].length).map((v, i) => {
          return <Seat
            key={`S23 17열 ${v}`} top='519px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 17열 ${v}`}</Data></Seat>
        })
      }
      {/* 18열 */}
      {
        seatData[10]['18열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`18열 ${v}`} top='539px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 18열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[10]['18열'].slice(16, 20).map((v, i) => {
          return <Seat
            key={`S23 18열 ${v}`} top='539px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 18열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[10]['18열'].slice(20, 24).map((v, i) => {
          return <Seat
            key={`S23 18열 ${v}`} top='539px' left={`${480 - (i * 15)}px`} >
            <Data>{`S23 18열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[10]['18열'].slice(24, seatData[10]['18열'].length).map((v, i) => {
          return <Seat
            key={`S23 18열 ${v}`} top='539px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 18열 ${v}`}</Data></Seat>
        })
      }
      {/* 19열 */}
      {
        seatData[11]['19열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`19열 ${v}`} top='559px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 19열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[11]['19열'].slice(16, 20).map((v, i) => {
          return <Seat
            key={`S23 19열 ${v}`} top='559px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 19열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[11]['19열'].slice(20, 24).map((v, i) => {
          return <Seat
            key={`S23 19열 ${v}`} top='559px' left={`${480 - (i * 15)}px`} >
            <Data>{`S23 19열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[11]['19열'].slice(24, seatData[11]['19열'].length).map((v, i) => {
          return <Seat
            key={`S23 19열 ${v}`} top='559px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 19열 ${v}`}</Data></Seat>
        })
      }
      {/* 20열 */}
      {
        seatData[12]['20열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`20열 ${v}`} top='579px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 20열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[12]['20열'].slice(16, 32).map((v, i) => {
          return <Seat
            key={`S23 20열 ${v}`} top='579px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 20열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[12]['20열'].slice(32, 48).map((v, i) => {
          return <Seat
            key={`S23 20열 ${v}`} top='579px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 20열 ${v}`}</Data></Seat>
        })
      }
      {/* 21열 */}
      {
        seatData[13]['21열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`21열 ${v}`} top='599px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 21열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[13]['21열'].slice(16, 32).map((v, i) => {
          return <Seat
            key={`S23 21열 ${v}`} top='599px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 21열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[13]['21열'].slice(32, 48).map((v, i) => {
          return <Seat
            key={`S23 21열 ${v}`} top='599px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 21열 ${v}`}</Data></Seat>
        })
      }
      {/* 22열 */}
      {
        seatData[14]['22열'].slice(0, 16).map((v, i) => {
          return <Seat
            key={`22열 ${v}`} top='619px' left={`${935 - (i * 15)}px`}
          ><Data>{`S23 22열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[14]['22열'].slice(16, 32).map((v, i) => {
          return <Seat
            key={`S23 22열 ${v}`} top='619px' left={`${660 - (i * 15)}px`} >
            <Data>{`S23 22열 ${v}`}</Data></Seat>
        })
      }
      {
        seatData[14]['22열'].slice(32, 48).map((v, i) => {
          return <Seat
            key={`S23 22열 ${v}`} top='619px' left={`${390 - (i * 15)}px`} >
            <Data>{`S23 22열 ${v}`}</Data></Seat>
        })
      }
    </Sector>
  );
}