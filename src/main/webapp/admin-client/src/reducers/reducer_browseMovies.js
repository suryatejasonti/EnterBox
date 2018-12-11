import {GET_MOVIES_FAILURE, GET_MOVIES_SUCCESS} from "../actions/satishActions";

export default function (state={},action){
  // console.log("inside Search users reducer",action.payload);
  switch(action.type){
    case GET_MOVIES_SUCCESS:
      console.log(action.payload.data);
      return {...state,  movies: (action.payload.data)};
      break;
    case GET_MOVIES_FAILURE:
      return {...state, message: 'no data found'};
      break;
    default:
      return {...state};
  }
}
