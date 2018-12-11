import React, { Component } from 'react';
import {connect} from "react-redux";
import {addMovies} from  '../actions/pranithActions';
import {Field, reduxForm, initialize, formValueSelector} from 'redux-form';
import {bindActionCreators} from 'redux';
import '../assets/css/userProfile.css'
import * as API from "../api/API";



class AddMovies extends Component {

    constructor(props){
        super(props)
    }
    state = {
      movietype : ''
    }
    componentWillMount() {
        API.fetchUser()
            .then((res) => {
                console.log(res);

                if (!res.user) {
                    this.props.history.push('/login')
                }


            });
    }

    onSubmit(values){
        console.log(values);
        this.props.addMovies(values);
    }


    renderField(field){
        const { meta:{touched,error}} = field;
        const cname = `form-bar ${touched && error? 'has-danger' : ''} `;

        return(
            <div className= {cname}>
                {/*<label>{field.label}</label>*/}
                <input {...field.input} {...field}
                />
                <div className="text-help">
                    {touched ? error: ''}
                </div>
            </div>
        )

    }
    renderDescr(field) {
        const {meta: {touched, error}} = field;
        const cname = `form-bar ${touched && error ? 'has-danger' : ''} `;

        return (
            <div className={cname}>
                {/*<label>{field.label}</label>*/}
                <textarea {...field.input} {...field}
                />
                <div className="text-help">
                    {touched ? error : ''}
                </div>
            </div>
        )
    }
    renderSelect(field){
        return(
            <div>
                <select {...field.input} {...field}/>
                {field.touched && field.error && <div className="error">{field.error}</div>}
            </div>
        );
    }

    render() {
        const {handleSubmit, load, pristine, reset, submitting} = this.props;
        //var msg = this.props.isMovieAdded?"Successful":"Failed";,
        // console.log("Movie Status: ", this.props.movieStatus);
        return (
            <div>
              <div className='row'>
                <div id='addMovieHalls' className='medium-8 columns col-md-offset-1'>
                  <form className="registration-form" onSubmit={handleSubmit(this.onSubmit.bind(this))}>
                    <p className="join-header">JOIN Entertainment Box<span
                      className="page-header-emphasis">VIP</span>
                      {/*<span className="registration-caption hide-for-small-only text-danger"> {this.props.movieStatus}</span>*/}
                    </p>

                    <div className='form-group'>

                      {/* Movie Theater Name */}
                      <label>Movie Title</label>
                      <Field name="title"
                             className="form-control form-control-lg registration-form-movietheatername"
                             id="MovieTitle"
                             type='text'
                             component={this.renderField}
                      />

                      <label>Movie Year</label>
                      <Field name="year"
                             className="form-control form-control-lg registration-form-movietheatername"
                             id="MovieYear"
                             type='text'
                             component={this.renderField}
                      />
                      <label>Movie Type</label>
                      <Field name ="movieType" component={this.renderSelect}  className="form-control form-control-lg registration-form-movietheatername">
                        <option></option>
                        <option value="paid" >Paid</option>
                        <option value="free">Free</option>
                        <option value="subscriptionOnly">Subscription Only</option>
                        <option value="payPerViewOnly">Pay Per View Only</option>
                      </Field>

                      <label>Director</label>
                      <Field name="directorName"
                             className="form-control form-control-lg registration-form-movietheatername"
                             id="Moviedirector"
                             type='text'
                             component={this.renderField}
                      />
                      <label>Actors</label>
                      <Field name="actors"
                             className="form-control form-control-lg registration-form-movietheatername"
                             id="actors"
                             type='text'
                             placeholder = 'Enter Actors names with comma seperated values'
                             component={this.renderField}
                      />
                      <label>Movie Url</label>
                      <Field name="movieUrl"
                             className="form-control form-control-lg registration-form-movietheatername"
                             id="movieUrl"
                             type='text'
                             component={this.renderField}
                      />
                      <div>
                      <label>Price</label>
                      <Field name="price"
                             className="form-control form-control-lg registration-form-movietheatername"
                             id="price"
                             type='text'
                             value = '0'
                             component={this.renderField}
                      />
                      </div>
                      <label>Mpaa Rating</label>
                      <Field name ="mpparating" component={this.renderSelect}  className="form-control form-control-lg registration-form-movietheatername">
                        <option></option>
                        <option value="G" >G – General Audiences</option>
                        <option value="PG">PG – Parental Guidance Suggested</option>
                        <option value="PG-13">PG-13 – Parents Strongly Cautioned</option>
                        <option value="NC-17">NC-17 – Adults Only</option>
                      </Field>
                      {/* City */}
                      <label>Synopsis</label>
                      <Field name="synopsis"
                             className="form-control form-control-lg registration-form-city"
                             id="Synopsis"
                             type='text'
                             component={this.renderField}
                      />

                      {/* State - Dropdown */}
                      <label>Country</label>
                      <Field name="country"
                             className="form-control form-control-lg registration-form-country"
                             id="country"
                             type='text'
                             component={this.renderField}
                      />

                      {/* ZIPCODE */}
                      <label>genre</label>
                      <Field name="genre"
                             className="form-control form-control-lg registration-form-zipcode"
                             id="genre"
                             type='text'
                             component={this.renderField}
                      />

                      {/* Hall Owner - firstname */}
                      <label>image</label>
                      <Field name="image"
                             className="form-control form-control-lg registration-form-firstname"
                             id="image"
                             type='text'
                             component={this.renderField}
                      />
                      {/* Hall Owner -lastname */}
                      <label>Studio</label>
                      <Field name="studio"
                             className="form-control form-control-lg registration-form-lastname"
                             id="studio"
                             type='text'
                             component={this.renderField}
                      />
                      {/* Hall Owner -emailID */}
                      <div className="form-group">
                        <button id="registration-form-submit" className="btn-cta full-width vip-join-now">
                          <span className="post-project-btn-font">ADD MOVIE</span>
                        </button>
                      </div>


                    </div>
                  </form>
                </div>
              </div>


            </div>
        )
    }

}
function validate(values) {
    const errors = {};

    let regEx_zipcode = RegExp('^[0-9]{5}(?:-[0-9]{4})?$');
    //validate input from value

    if (!values.title) {
        errors.title = "Please enter Movie Name\n ";
    }
  if ((!values.year)) {
    errors.year = "Please enter a movie year\n ";
  }

    if ((values.year)) {
      if(isNaN(values.year)){
        errors.year = "Please enter a Integer value for year\n ";
      }
    }

    if (!values.movieType){
        errors.movieType = "Please enter a movie type as paid or Free\n";
    }
  if (!values.moviedirector){
    errors.moviedirector = "Please enter a movie director\n";
  }

    if (!values.actors) {
        errors.actors = 'Enter aleast one actor\n';
    }
    if ((!values.price) && (values.movietype === 'free')) {
        values.price = 0
    }
    if ((!values.price) && (values.movietype === 'paid')) {
      errors.price = 'Enter a price for the movie'
    }
    if (!values.mpparating){
        errors.mpparating = 'Enter Mpaa Rating for the movie\n';
    }
    if (!values.country){
        errors.country = 'Enter country for the movie\n';
    }
    if (!values.genre){
      errors.genre = 'Enter genre for the movie\n';
    }
    if(!values.image){
      errors.genre = 'Enter image url for the movie\n';
    }
    if((!values.synopsis)){
      errors.synopsis = 'Enter Synopsis the movie\n';
    }
    if(values.synopsis && (values.synopsis.length > 2000)){
      errors.synopsis = 'Synopsis should be less than 2000 chars the movie\n';
    }
    if(!values.genre) {
      errors.genre = 'Enter genre for the movie\n';
    }
    if((values.actors) ){
      let format = /[!@#$%^&*()_+\-=\[\]{};':"\\|.<>\/?]/;
      if((values.actors.includes(','))){
        console.log("inside actors");
        values.actors = values.actors.split(',');
      }
      else if(format.test(values.actors)){
        errors.actors = 'Actors value has illegal characters special characters'
      }
      else{
        values.actors = [values.actors.toString()]
      }
  }

  if(!values.movieUrl) {
    errors.movieUrl = 'Enter video Link for the movie\n';
  }
    //if errors is empty , the form is fine to submit
    //if errors has *any* properties, redux form assumes that form is invalid
    return errors;
}

function mapStateToProps(state) {
    return {

        // movieStatus: state.addmoviehall.addMoviesAdmin

    }
}

function mapDispatchToProps(dispatch){
    return {
        ...bindActionCreators({

        },dispatch)
    }
}


export default reduxForm({
    validate,
    form: 'addMovie'
})
(connect(null,{addMovies})(AddMovies));
